import java.util.List;
import java.util.regex.Pattern;

// Localiza termos suspeitos, endereços e domínios dentro da mensagem.
public class AnalisadorConteudoMensagem {
    private static final Pattern VALOR_NUMERICO = Pattern.compile(
            "\\b(?:r\\$\\s*)?"
                    + "(?:\\d{1,3}(?:\\.\\d{3})+|\\d+)"
                    + "(?:,\\d{2})?"
                    + "\\s*(?:reais|real|pila|pilas|conto|contos)?\\b",
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

    private String removerPontuacaoFinal(String palavra) {
        return palavra
                .replace(",", "")
                .replace(";", "")
                .replace("!", "")
                .replace("?", "");
    }
}
