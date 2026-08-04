package modelos;

import java.util.ArrayList;
import java.util.List;

// Coordena a análise da mensagem e aplica as regras de pontuação.
public final class DetectorGolpes {
    private final NormalizadorMensagem normalizador = new NormalizadorMensagem();
    private final AnalisadorConteudoMensagem analisadorConteudo =
            new AnalisadorConteudoMensagem();
    private final AnalisadorTransferencia analisadorTransferencia =
            new AnalisadorTransferencia(analisadorConteudo);
    private final ClassificadorRisco classificadorRisco = new ClassificadorRisco();

    public ResultadoAnalise analisar(String mensagem) {
        validarMensagem(mensagem);

        String mensagemNormalizada = normalizador.normalizar(mensagem);

        // Cada variável abaixo representa um sinal que pode alterar a pontuação.
        boolean contemPressaoOuAcao = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_PRESSAO_OU_ACAO
        );
        boolean contemPremioOuPromessa = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_PREMIO_OU_PROMESSA
        );
        boolean contemDadosOuIdentidade = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_DADOS_OU_IDENTIDADE
        );
        boolean contemLink = analisadorConteudo.contemLink(
                mensagemNormalizada,
                CatalogoTermosGolpe.INDICIOS_DE_LINK,
                CatalogoTermosGolpe.DOMINIOS_DE_LINK
        );
        boolean contemTermosFinanceiros = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_FINANCEIROS
        );
        boolean contemMovimentacaoFinanceira = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_MOVIMENTACAO_FINANCEIRA
        ) || analisadorConteudo.contemChavePixEmail(mensagemNormalizada);
        boolean contemAmeacaDigital = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_AMEACA_DIGITAL
        );

        boolean transferenciaSuspeita =
                analisadorTransferencia.contemTransferenciaSuspeita(mensagemNormalizada);
        boolean envioLegitimo =
                analisadorTransferencia.contemEnvioLegitimo(mensagemNormalizada);

        // Uma transferência já identificada aqui não deve receber pontos duplicados.
        boolean transferenciaEspecial = transferenciaSuspeita || envioLegitimo;

        boolean pedidoFinanceiro = !transferenciaEspecial
                && (contemTermosFinanceiros
                || analisadorConteudo.contemValorNumerico(mensagemNormalizada));
        boolean transferenciaComum =
                !transferenciaEspecial && contemMovimentacaoFinanceira;
        boolean combinacaoPromocional = contemPremioOuPromessa
                && (contemLink || pedidoFinanceiro);

        int pontuacao = 0;
        List<String> motivos = new ArrayList<>();

        pontuacao += registrarSinal(
                contemPressaoOuAcao,
                2,
                "A mensagem cria pressão ou solicita uma ação imediata.",
                motivos
        );
        pontuacao += registrarSinal(
                contemPremioOuPromessa,
                2,
                "A mensagem oferece prêmio, benefício ou dinheiro fácil.",
                motivos
        );
        pontuacao += registrarSinal(
                combinacaoPromocional,
                2,
                "A mensagem combina uma promessa com link ou pedido financeiro.",
                motivos
        );
        pontuacao += registrarSinal(
                contemLink,
                3,
                "A mensagem contém um link.",
                motivos
        );
        pontuacao += registrarSinal(
                transferenciaComum,
                3,
                "A mensagem solicita uma transferência de dinheiro.",
                motivos
        );
        pontuacao += registrarSinal(
                contemDadosOuIdentidade,
                4,
                "A mensagem solicita dados ou utiliza uma identidade ou contato suspeito.",
                motivos
        );
        pontuacao += registrarSinal(
                transferenciaSuspeita,
                4,
                "A mensagem descreve uma transferência inesperada e merece verificação.",
                motivos
        );
        pontuacao += registrarSinal(
                pedidoFinanceiro,
                5,
                "A mensagem menciona taxas, pagamentos, investimentos ou pedidos de dinheiro.",
                motivos
        );
        pontuacao += registrarSinal(
                contemAmeacaDigital,
                7,
                "A mensagem solicita acesso ao dispositivo ou contém um arquivo perigoso.",
                motivos
        );

        return new ResultadoAnalise(
                classificadorRisco.classificar(pontuacao),
                pontuacao,
                motivos
        );
    }

    private int registrarSinal(
            boolean sinalEncontrado,
            int pontos,
            String motivo,
            List<String> motivos
    ) {
        if (!sinalEncontrado) {
            return 0;
        }

        motivos.add(motivo);
        return pontos;
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }
    }
}
