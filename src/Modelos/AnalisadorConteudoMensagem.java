package Modelos;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

// Localiza termos suspeitos, endereços e domínios dentro da mensagem.
public class AnalisadorConteudoMensagem {
    private static final Pattern VALOR_NUMERICO = Pattern.compile(
            "\\b(?:"
                    // Opção 1: Começa com R$ (ex: R$ 50, R$ 50,00, R$ 1.000)
                    + "(?:r\\$\\s*\\d+(?:\\.\\d{3})*(?:,\\d{2})?)"
                    // Opção 2: Número com centavos (ex: 50,00, 1.500,50)
                    + "|(?:\\d{1,3}(?:\\.\\d{3})+|\\d+),\\d{2}"
                    // Opção 3: Número seguido de moeda/gíria (ex: 50 reais, 10 pilas, 5 contos)
                    + "|(?:\\d{1,3}(?:\\.\\d{3})+|\\d+)\\s*(?:reais|real|pila|pilas|conto|contos)"
                    + ")\\b",
            Pattern.CASE_INSENSITIVE
    );

    public boolean contemAlgumTermo(String mensagem, List<String> termos) {
        for (String termo : termos) {
            if (mensagem.contains(termo)) {
                return true;
            }
        }

        return false;
    }

    public boolean contemLink(String mensagem) {
        if (contemAlgumTermo(mensagem, CatalogoTermosGolpe.INDICIOS_DE_LINK)) {
            return true;
        }

        String[] palavras = mensagem.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.contains("@")) {
                continue;
            }

            String palavraSemPontuacao = removerPontuacaoFinal(palavra);

            for (String dominio : CatalogoTermosGolpe.DOMINIOS_DE_LINK) {
                if (palavraSemPontuacao.endsWith(dominio)
                        || palavraSemPontuacao.contains(dominio + "/")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean contemPedidoDeValor(String mensagem) {
            return VALOR_NUMERICO.matcher(mensagem).find()
                    || contemAlgumTermo(
                    mensagem,
                    CatalogoTermosGolpe.TERMOS_PEDIDO_DE_VALOR
            );
    }

    public boolean contemDeclaracaoEnvioLegitimo(String mensagem) {
        String mensagemNormalizada = mensagem.toLowerCase(Locale.ROOT);

        if (mensagemNormalizada.contains("estou te mandando")
                || mensagemNormalizada.contains("estou mandando")
                || mensagemNormalizada.contains("estou te enviando")) {
            return false;
        }

        String[] declaracoesAutonomas = {
                "vou enviar",
                "vou mandar",
                "vou te enviar",
                "vou te mandar",
                "estou enviando",
                "te envio",
                "te mando",
                "te mandando",
                "vou fazer um pix",
                "vou fazer o pix",
                "vou transferir",
                "vou fazer uma transferencia",
                "vou pagar",
                "vou te pagar",
                "envio um pix",
                "envio o pix",
                "mando um pix",
                "mando o pix",
                "enviei um pix",
                "enviei",
                "mandei um pix",
                "mandei",
                "estou te pagando",
                "devo"
        };

        boolean temDeclaracaoAutonoma = false;
        for (String declaracao : declaracoesAutonomas) {
            if (mensagemNormalizada.contains(declaracao)) {
                temDeclaracaoAutonoma = true;
                break;
            }
        }

        boolean temIndicadorTransferencia = mensagemNormalizada.contains("pix")
                || mensagemNormalizada.contains("transferencia")
                || mensagemNormalizada.contains("transferir")
                || mensagemNormalizada.contains("dinheiro")
                || mensagemNormalizada.contains("reais")
                || mensagemNormalizada.contains("real")
                || VALOR_NUMERICO.matcher(mensagemNormalizada).find();

        return temDeclaracaoAutonoma && temIndicadorTransferencia;
    }

    public boolean contemTransferenciaSuspeita(String mensagem) {
        String mensagemNormalizada = mensagem.toLowerCase(Locale.ROOT);
        boolean temPadraoSuspeito = mensagemNormalizada.contains("estou te mandando")
                || mensagemNormalizada.contains("estou mandando")
                || mensagemNormalizada.contains("estou te enviando");

        boolean temIndicadorTransferencia = mensagemNormalizada.contains("pix")
                || mensagemNormalizada.contains("transferencia")
                || mensagemNormalizada.contains("transferir")
                || mensagemNormalizada.contains("dinheiro")
                || mensagemNormalizada.contains("reais")
                || mensagemNormalizada.contains("real")
                || VALOR_NUMERICO.matcher(mensagemNormalizada).find();

        return temPadraoSuspeito && temIndicadorTransferencia;
    }

    private String removerPontuacaoFinal(String palavra) {
        return palavra
                .replace(",", "")
                .replace(";", "")
                .replace("!", "")
                .replace("?", "");
    }
}
