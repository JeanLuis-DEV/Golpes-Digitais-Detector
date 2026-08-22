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

        pontuacao = ajustarPontuacaoDeRelato(
                textoNormalizado,
                pontuacao
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
        String textoContextual =
                analisador.removerLinks(textoNormalizado);

        boolean contemLink =
                analisador.contemLink(textoNormalizado);

        boolean contemLinkDesconhecido =
                analisador.contemLinkDesconhecido(
                        textoNormalizado
                );

        boolean contemImitacaoDeDominioOficial =
                analisador.contemImitacaoDeDominioOficial(
                        textoNormalizado
                );

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

        boolean cartaoComprometido =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_CARTAO_COMPROMETIDO
                );

        boolean orientaCanalOficial =
                analisador.contemAlgumTermo(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_CANAL_OFICIAL
                );

        boolean pedidoDeCredenciais =
                analisador.contemAlgumTermoSemNegacao(
                        textoNormalizado,
                        CatalogoGolpes.TERMOS_PEDIDO_CREDENCIAIS
                );

        boolean possuiVerboDeSolicitacao =
                analisador.contemAlgumTermoSemNegacao(
                        textoContextual,
                        CatalogoGolpes.VERBOS_SOLICITACAO
                )
                        || analisador.contemRadicalSemNegacao(
                        textoContextual,
                        CatalogoGolpes.RADICAIS_SOLICITACAO
                );

        boolean possuiDadoSensivel =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.ALVOS_DADOS_SENSIVEIS
                );

        boolean possuiAcaoFinanceiraDireta =
                analisador.contemAlgumTermoSemNegacao(
                        textoContextual,
                        CatalogoGolpes.VERBOS_ACAO_FINANCEIRA
                );

        boolean possuiAcaoFinanceira =
                possuiAcaoFinanceiraDireta
                        || analisador.contemRadicalSemNegacao(
                        textoContextual,
                        CatalogoGolpes.RADICAIS_ACAO_FINANCEIRA
                );

        boolean possuiAlvoFinanceiro =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.ALVOS_FINANCEIROS
                )
                        || analisador.contemValorMonetario(
                        textoContextual
                );

        boolean possuiCredencialSecreta =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.ALVOS_CREDENCIAIS_SECRETAS
                );

        boolean possuiPretextoDeGolpe =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_PRETEXTO_DE_GOLPE
                );

        boolean possuiPromessaIrreal =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_PROMESSA_IRREAL
                );

        boolean possuiContextoDeInvestimento =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_CONTEXTO_INVESTIMENTO
                );

        boolean possuiSinalTecnico =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_ACESSO_TECNICO
                );

        boolean possuiCoacaoGrave =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_COACAO_GRAVE
                );

        boolean possuiIdentidadeFraudulenta =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_IDENTIDADE_FRAUDULENTA
                );

        boolean mensagemRelataSituacao =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.MARCADORES_DE_RELATO
                );

        boolean possuiSinalGeralDeRisco =
                analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.SINAIS_GERAIS_DE_RISCO
                );

        pontuacao += adicionarDeteccao(
                contemLink,
                3,
                "A mensagem contém um link.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                contemLinkDesconhecido,
                1,
                "O link usa um domínio que não consta na lista de canais oficiais cadastrados.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                contemImitacaoDeDominioOficial,
                4,
                "O link parece imitar o domínio de uma instituição ou comércio conhecido.",
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

        pontuacao += adicionarDeteccao(
                cartaoComprometido && !orientaCanalOficial,
                4,
                "A mensagem relata bloqueio ou uso suspeito de cartão que deve ser verificado.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                pedidoDeCredenciais,
                7,
                "A mensagem solicita credenciais bancárias ou códigos secretos de autenticação.",
                motivos
        );

        boolean solicitacaoEstruturalDeDados =
                !mensagemRelataSituacao
                        && !orientaCanalOficial
                        && possuiVerboDeSolicitacao
                        && possuiDadoSensivel;

        pontuacao += adicionarDeteccao(
                solicitacaoEstruturalDeDados,
                7,
                "A mensagem combina uma ordem ou solicitação com dados sensíveis.",
                motivos
        );

        boolean pedidoDeSegredoMesmoEmRelato =
                !orientaCanalOficial
                        && possuiVerboDeSolicitacao
                        && possuiCredencialSecreta;

        pontuacao += adicionarDeteccao(
                pedidoDeSegredoMesmoEmRelato,
                7,
                "A mensagem solicita senha, código, token ou outra credencial secreta.",
                motivos
        );

        boolean pedidoFinanceiroComPretexto =
                possuiAcaoFinanceiraDireta
                        && possuiAlvoFinanceiro
                        && (possuiPretextoDeGolpe
                        || possuiPromessaIrreal
                        || possuiContextoDeInvestimento);

        pontuacao += adicionarDeteccao(
                pedidoFinanceiroComPretexto,
                7,
                "A mensagem combina pagamento ou transferência com um pretexto típico de fraude.",
                motivos
        );

        boolean acessoTecnicoSolicitado =
                !mensagemRelataSituacao
                        && possuiVerboDeSolicitacao
                        && possuiSinalTecnico;

        pontuacao += adicionarDeteccao(
                acessoTecnicoSolicitado,
                7,
                "A mensagem solicita uma ação técnica que pode dar acesso ao dispositivo.",
                motivos
        );

        boolean promessaFinanceiraEstrutural =
                possuiPromessaIrreal
                        && (possuiAlvoFinanceiro
                        || possuiContextoDeInvestimento);

        pontuacao += adicionarDeteccao(
                promessaFinanceiraEstrutural,
                7,
                "A mensagem associa dinheiro ou investimento a uma promessa irreal.",
                motivos
        );

        boolean pretextoFinanceiroDireto =
                !mensagemRelataSituacao
                        && possuiPretextoDeGolpe
                        && possuiAlvoFinanceiro;

        pontuacao += adicionarDeteccao(
                pretextoFinanceiroDireto,
                7,
                "A mensagem relaciona um pretexto de fraude a dinheiro ou pagamento.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                possuiCoacaoGrave,
                7,
                "A mensagem contém ameaça grave, sequestro ou extorsão.",
                motivos
        );

        pontuacao += adicionarDeteccao(
                possuiIdentidadeFraudulenta
                        && possuiAlvoFinanceiro,
                7,
                "A mensagem combina uma identidade suspeita com necessidade financeira.",
                motivos
        );

        boolean linkComAcaoDeRisco =
                contemLinkDesconhecido
                        && !orientaCanalOficial
                        && !mensagemRelataSituacao
                        && possuiVerboDeSolicitacao
                        && (possuiDadoSensivel
                        || possuiPretextoDeGolpe
                        || possuiAcaoFinanceira);

        pontuacao += adicionarDeteccao(
                linkComAcaoDeRisco,
                4,
                "A mensagem combina um link com uma ação relacionada a dados, conta ou pagamento.",
                motivos
        );

        boolean linkComPretextoDireto =
                contemLinkDesconhecido
                        && !mensagemRelataSituacao
                        && possuiPretextoDeGolpe;

        pontuacao += adicionarDeteccao(
                linkComPretextoDireto,
                4,
                "A mensagem apresenta um domínio desconhecido junto de um pretexto de fraude.",
                motivos
        );

        boolean alertaContextual =
                !orientaCanalOficial
                        && !possuiTermosTransferencia
                        && (possuiSinalGeralDeRisco
                        || (mensagemRelataSituacao
                        && (possuiDadoSensivel
                        || possuiAlvoFinanceiro
                        || possuiPretextoDeGolpe
                        || possuiPromessaIrreal
                        || possuiContextoDeInvestimento
                        || possuiSinalTecnico
                        || contemLink)));

        pontuacao += adicionarDeteccao(
                alertaContextual,
                4,
                "A mensagem relata uma situação com sinais que exigem verificação da fonte.",
                motivos
        );

        boolean solicitacaoTransferencia =
                !transferenciaSuspeita
                        && !envioLegitimo
                        && possuiTermosTransferencia;

        pontuacao += adicionarDeteccao(
                solicitacaoTransferencia,
                4,
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

        boolean contatoNovoComTransferencia =
                mudancaContato && possuiTermosTransferencia;

        pontuacao += adicionarDeteccao(
                contatoNovoComTransferencia,
                4,
                "A mensagem combina mudança de contato com uma transferência financeira.",
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

    /**
     * Evita que vários sinais moderados, todos descrevendo o mesmo fato,
     * sejam somados como se fossem evidências independentes de golpe.
     */
    private int ajustarPontuacaoDeRelato(
            String textoNormalizado,
            int pontuacao
    ) {
        String textoContextual =
                analisador.removerLinks(textoNormalizado);

        boolean relato = analisador.contemAlgumTermo(
                textoContextual,
                CatalogoGolpes.MARCADORES_DE_RELATO
        );

        boolean sinalGeral = analisador.contemAlgumTermo(
                textoContextual,
                CatalogoGolpes.SINAIS_GERAIS_DE_RISCO
        );

        boolean orientaCanalOficial = analisador.contemAlgumTermo(
                textoContextual,
                CatalogoGolpes.TERMOS_CANAL_OFICIAL
        );

        boolean possuiPedidoDeSegredo =
                (analisador.contemAlgumTermoSemNegacao(
                        textoContextual,
                        CatalogoGolpes.VERBOS_SOLICITACAO
                )
                        || analisador.contemRadicalSemNegacao(
                        textoContextual,
                        CatalogoGolpes.RADICAIS_SOLICITACAO
                ))
                        && analisador.contemAlgumTermo(
                        textoContextual,
                        CatalogoGolpes.ALVOS_CREDENCIAIS_SECRETAS
                );

        boolean evidenciaCritica = possuiPedidoDeSegredo
                || analisador.contemArquivoPerigoso(textoNormalizado)
                || contemRegraSimplesDeAltoRisco(textoContextual)
                || analisador.contemAlgumTermo(
                textoContextual,
                CatalogoGolpes.SINAIS_COACAO_GRAVE
        )
                || analisador.contemAlgumTermo(
                textoContextual,
                CatalogoGolpes.SINAIS_IDENTIDADE_FRAUDULENTA
        );

        if (!evidenciaCritica
                && (orientaCanalOficial
                || (relato && sinalGeral))) {
            return Math.min(pontuacao, 6);
        }

        return pontuacao;
    }

    private boolean contemRegraSimplesDeAltoRisco(
            String textoNormalizado
    ) {
        for (RegraGolpe regra : CatalogoGolpes.REGRAS_SIMPLES) {
            if (regra.pontos() >= LIMITE_RISCO_ALTO
                    && analisador.contemAlgumTermo(
                    textoNormalizado,
                    regra.termos()
            )) {
                return true;
            }
        }

        return false;
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
