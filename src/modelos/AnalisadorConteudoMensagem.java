package modelos;

import java.util.List;
import java.util.regex.Pattern;

// Oferece somente operações genéricas de busca dentro de uma mensagem.
public final class AnalisadorConteudoMensagem {
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
    private static final Pattern CHAVE_PIX_EMAIL = Pattern.compile(
            "\\b[\\p{L}\\p{N}._%+-]*pix[\\p{L}\\p{N}._%+-]*@"
    );
    private static final Pattern PONTUACAO_FINAL = Pattern.compile("[\\p{Punct}&&[^/]]+$");

    public boolean contemAlgumTermo(String mensagem, List<String> termos) {
        for (String termo : termos) {
            if (contemTermo(mensagem, termo)) {
                return true;
            }
        }

        return false;
    }

    public boolean contemLink(
            String mensagem,
            List<String> indiciosDeLink,
            List<String> dominiosDeLink
    ) {
        if (contemAlgumTermo(mensagem, indiciosDeLink)) {
            return true;
        }

        String[] palavras = mensagem.split("\\s+");

        for (String palavra : palavras) {
            if (!palavra.contains("@") && pareceDominio(palavra, dominiosDeLink)) {
                return true;
            }
        }

        return false;
    }

    public boolean contemValorNumerico(String mensagem) {
        return VALOR_NUMERICO.matcher(mensagem).find();
    }

    public boolean contemChavePixEmail(String mensagem) {
        return CHAVE_PIX_EMAIL.matcher(mensagem).find();
    }

    private boolean contemTermo(String mensagem, String termo) {
        int inicio = mensagem.indexOf(termo);

        while (inicio >= 0) {
            int fim = inicio + termo.length();
            boolean limiteInicialValido = !Character.isLetterOrDigit(termo.charAt(0))
                    || inicio == 0
                    || !Character.isLetterOrDigit(mensagem.charAt(inicio - 1));
            boolean limiteFinalValido = !Character.isLetterOrDigit(termo.charAt(termo.length() - 1))
                    || fim == mensagem.length()
                    || !Character.isLetterOrDigit(mensagem.charAt(fim));

            if (limiteInicialValido && limiteFinalValido) {
                return true;
            }

            inicio = mensagem.indexOf(termo, inicio + 1);
        }

        return false;
    }

    private boolean pareceDominio(String palavra, List<String> dominiosDeLink) {
        String palavraSemPontuacao = PONTUACAO_FINAL.matcher(palavra).replaceAll("");
        return dominiosDeLink.stream().anyMatch(
                dominio -> palavraSemPontuacao.endsWith(dominio)
                        || palavraSemPontuacao.contains(dominio + "/")
        );
    }
}
