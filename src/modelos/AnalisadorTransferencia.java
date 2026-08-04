package modelos;

import java.util.List;

// Identifica os padrões específicos relacionados a transferências financeiras.
public final class AnalisadorTransferencia {
    private static final List<String> DECLARACOES_DE_ENVIO = List.of(
            "vou enviar", "vou mandar", "vou te enviar", "vou te mandar",
            "estou enviando", "te envio", "te mando", "te mandando",
            "vou fazer um pix", "vou fazer o pix", "vou transferir",
            "vou fazer uma transferencia", "vou pagar", "vou te pagar",
            "envio um pix", "envio o pix", "mando um pix", "mando o pix",
            "enviei um pix", "enviei", "mandei um pix", "mandei",
            "estou te pagando", "devo"
    );

    private static final List<String> PADROES_SUSPEITOS = List.of(
            "estou te mandando",
            "estou mandando",
            "estou te enviando"
    );

    private static final List<String> INDICADORES_DE_TRANSFERENCIA = List.of(
            "pix",
            "transferencia",
            "transferir",
            "dinheiro",
            "reais",
            "real"
    );

    private final AnalisadorConteudoMensagem analisadorConteudo;

    public AnalisadorTransferencia(AnalisadorConteudoMensagem analisadorConteudo) {
        this.analisadorConteudo = analisadorConteudo;
    }

    public boolean contemEnvioLegitimo(String mensagem) {
        return !contemPadraoSuspeito(mensagem)
                && contemDeclaracaoDeEnvio(mensagem)
                && contemIndicadorFinanceiro(mensagem);
    }

    public boolean contemTransferenciaSuspeita(String mensagem) {
        return contemPadraoSuspeito(mensagem)
                && contemIndicadorFinanceiro(mensagem);
    }

    private boolean contemDeclaracaoDeEnvio(String mensagem) {
        return analisadorConteudo.contemAlgumTermo(
                mensagem,
                DECLARACOES_DE_ENVIO
        );
    }

    private boolean contemPadraoSuspeito(String mensagem) {
        return analisadorConteudo.contemAlgumTermo(
                mensagem,
                PADROES_SUSPEITOS
        );
    }

    private boolean contemIndicadorFinanceiro(String mensagem) {
        return analisadorConteudo.contemAlgumTermo(mensagem, INDICADORES_DE_TRANSFERENCIA)
                || analisadorConteudo.contemValorNumerico(mensagem)
                || analisadorConteudo.contemChavePixEmail(mensagem);
    }
}
