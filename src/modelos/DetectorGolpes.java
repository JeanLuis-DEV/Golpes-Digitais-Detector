package Modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordena todo o processo de análise.
 * <p>
 * O detector:
 * 1. valida a entrada;
 * 2. normaliza o texto;
 * 3. executa as regras simples;
 * 4. executa as regras compostas;
 * 5. classifica o resultado.
 */
public final class DetectorGolpes {

    private static final int LIMITE_RISCO_MEDIO = 4;
    private static final int LIMITE_RISCO_ALTO = 7;

    private final AnalisadorMensagem analisador =
            new AnalisadorMensagem();

    public ResultadoAnalise analisar(String mensagem) {
        validarMensagem(mensagem);

        String textoNormalizado =
                analisador.normalizar(mensagem);

        List<String> motivos = new ArrayList<>();

        int pontuacao = aplicarRegrasSimples(
                textoNormalizado,
                motivos
        );

        pontuacao += aplicarRegrasCompostas(
                textoNormalizado,
                motivos
        );

        String nivelRisco = classificar(pontuacao);

        return new ResultadoAnalise(
                nivelRisco,
                pontuacao,
                motivos
        );
    }

    /**
     * Aplica todas as regras que dependem apenas da presença
     * de termos.
     */
    private int aplicarRegrasSimples(
            String textoNormalizado,
            List<String> motivos
    ) {
        int pontuacao = 0;

        for (RegraGolpe regra : CatalogoGolpes.REGRAS_SIMPLES) {
            boolean detectado = analisador.contemAlgumTermo(
                    textoNormalizado,
                    regra.termos()
            );

            if (detectado) {
                pontuacao += regra.pontos();
                motivos.add(regra.motivo());
            }
        }

        return pontuacao;
    }

    /**
     * Aplica regras que dependem da combinação de condições.
     */
    private int aplicarRegrasCompostas(
            String textoNormalizado,
            List<String> motivos
    ) {
        int pontuacao = 0;

        boolean contemLink =
                analisador.contemLink(textoNormalizado);

        boolean transferenciaSuspeita =
                analisador.contemTransferenciaSuspeita(
                        textoNormalizado
                );

        boolean envioLegitimo =
                analisador.contemDeclaracaoEnvioLegitimo(
                        textoNormalizado
                );

        boolean possuiTermosTransferencia =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_TRANSFERENCIA
                );

        boolean pedidoDinheiro =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.PEDIDOS_DINHEIRO
                );

        boolean pedidoValor =
                analisador.contemPedidoDeValor(
                        textoNormalizado
                );

        boolean promessaFinanceira =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_PROMESSA_FINANCEIRA
                );

        pontuacao += adicionarDeteccao(
                contemLink,
                3,
                "A mensagem contém um link.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                transferenciaSuspeita,
                4,
                "A mensagem descreve uma transferência inesperada e merece verificação.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                promessaFinanceira && contemLink,
                4,
                "A mensagem combina um link com uma promessa financeira.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                analisador.contemArquivoPerigoso(
                        textoNormalizado
                ),
                7,
                "A mensagem contém um arquivo que pode executar programas maliciosos.",
                motivos
        );

        boolean solicitacaoTransferencia =
                !transferenciaSuspeita
                        && !envioLegitimo
                        && possuiTermosTransferencia;

        pontuacao += adicionarDeteccao(
                solicitacaoTransferencia,
                3,
                "A mensagem solicita ou menciona uma transferência de dinheiro.",
                motivos
        );

        boolean pedidoDiretoDeDinheiro =
                !transferenciaSuspeita
                        && !envioLegitimo
                        && pedidoValor
                        && pedidoDinheiro;

        pontuacao += adicionarDeteccao(
                pedidoDiretoDeDinheiro,
                4,
                "A mensagem contém um pedido de dinheiro.",
                motivos
        );

        boolean mencaoOuPedidoDeValor =
                !transferenciaSuspeita
                        && !envioLegitimo
                        && pedidoValor;

        pontuacao += adicionarDeteccao(
                mencaoOuPedidoDeValor,
                2,
                "A mensagem solicita ou menciona um valor em dinheiro.",
                motivos
        );

        boolean mudancaContato =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_MUDANCA_CONTATO
                );

        boolean pedidoFinanceiro =
                analisador.contemPedidoFinanceiro(
                        textoNormalizado
                );

        boolean contatoNovoComPedidoFinanceiro =
                mudancaContato && pedidoFinanceiro;

        pontuacao += adicionarDeteccao(
                contatoNovoComPedidoFinanceiro,
                3,
                "A mensagem combina uma mudança inesperada de contato com um pedido financeiro.",
                motivos
        );

        return pontuacao;


    }

    /**
     * Substitui a antiga combinação de Verificador,
     * VerificadorMotivo e MapMotivos.
     */
    private int adicionarDeteccao(
            boolean condicao,
            int pontos,
            String motivo,
            List<String> motivos
    ) {
        if (!condicao) {
            return 0;
        }

        motivos.add(motivo);
        return pontos;
    }

    private String classificar(int pontuacao) {
        if (pontuacao >= LIMITE_RISCO_ALTO) {
            return "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)";
        }

        if (pontuacao >= LIMITE_RISCO_MEDIO) {
            return "SUSPEITO(VERIFIQUE A FONTE)";
        }

        return "POSSIVELMENTE LEGÍTIMO";
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException(
                    "A mensagem não pode estar vazia."
            );
        }
    }
}