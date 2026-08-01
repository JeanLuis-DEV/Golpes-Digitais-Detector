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
                    // Opção 2: Símbolos de moedas internacionais (ex: $100, €50, £1000)
                    + "|(?:[\\$€£¥]\\s*\\d+(?:[.,]\\d{2})?)"
                    // Opção 3: Número com centavos (ex: 50,00, 1.500,50)
                    + "|(?:\\d{1,3}(?:\\.\\d{3})+|\\d+),\\d{2}"
                    // Opção 4: Número seguido de moeda/gíria (ex: 50 reais, 10 pilas, 5 contos)
                    + "|(?:\\d{1,3}(?:\\.\\d{3})+|\\d+)\\s*(?:reais|real|pila|pilas|conto|contos)"
                    // Opção 5: Número seguido de códigos de moedas internacionais (ex: 100 USD, 50 EUR, 1000 GBP, 5 BTC)
                    + "|(?:\\d+(?:[.,]\\d{2})?)\\s*(?:usd|eur|gbp|btc|eth|jpy|cny|inr|brl)"
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
                || contemMoedas(mensagemNormalizada)
                || VALOR_NUMERICO.matcher(mensagemNormalizada).find();

        return temDeclaracaoAutonoma && temIndicadorTransferencia;
    }

    public boolean contemTransferenciaSuspeita(String mensagem) {
       String mensagemNormalizada = mensagem.toLowerCase(Locale.ROOT);
       boolean temPadraoSuspeito = mensagemNormalizada.contains("estou te mandando")
               || mensagemNormalizada.contains("estou mandando")
               || mensagemNormalizada.contains("estou te enviando");

       boolean temIndicadorTransferencia = contemIndicadoresTransferencia(mensagemNormalizada);

       return temPadraoSuspeito && temIndicadorTransferencia;
    }

    private boolean contemIndicadoresTransferencia(String mensagemNormalizada) {
       return mensagemNormalizada.contains("pix")
               || mensagemNormalizada.contains("transferencia")
               || mensagemNormalizada.contains("transferir")
               || mensagemNormalizada.contains("dinheiro")
               || contemMoedas(mensagemNormalizada)
               || VALOR_NUMERICO.matcher(mensagemNormalizada).find();
    }

    private boolean contemMoedas(String mensagemNormalizada) {
       return contemMoedasBrasileiras(mensagemNormalizada)
               || contemMoedasInternacionais(mensagemNormalizada)
               || contemCryptomoedas(mensagemNormalizada);
    }

    private boolean contemMoedasBrasileiras(String mensagemNormalizada) {
       return mensagemNormalizada.contains("real")
               || mensagemNormalizada.contains("reais");
    }

    private boolean contemMoedasInternacionais(String mensagemNormalizada) {
       return mensagemNormalizada.contains("dolar")
               || mensagemNormalizada.contains("dolares")
               || mensagemNormalizada.contains("usd")
               || mensagemNormalizada.contains("euro")
               || mensagemNormalizada.contains("euros")
               || mensagemNormalizada.contains("eur")
               || mensagemNormalizada.contains("libra")
               || mensagemNormalizada.contains("libras")
               || mensagemNormalizada.contains("gbp")
               || mensagemNormalizada.contains("peso")
               || mensagemNormalizada.contains("pesos")
               || mensagemNormalizada.contains("yen")
               || mensagemNormalizada.contains("franco")
               || mensagemNormalizada.contains("francos");
    }

    private boolean contemCryptomoedas(String mensagemNormalizada) {
       return mensagemNormalizada.contains("bitcoin")
               || mensagemNormalizada.contains("btc")
               || mensagemNormalizada.contains("ethereum")
               || mensagemNormalizada.contains("eth")
               || mensagemNormalizada.contains("cripto")
               || mensagemNormalizada.contains("criptomoeda");
    }

    private String removerPontuacaoFinal(String palavra) {
        return palavra
                .replace(",", "")
                .replace(";", "")
                .replace("!", "")
                .replace("?", "");
    }
}
