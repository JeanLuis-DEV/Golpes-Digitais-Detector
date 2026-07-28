package modelos;
import static modelos.MensagensGolpes.DOMINIOS_DE_LINK;
import static modelos.MensagensGolpes.INDICIOS_DE_LINK;

public class ContemTexto {
    public boolean contemAlgumTermo(String mensagem, String[] termos) {
        for (String termo : termos) {
            if (mensagem.contains(termo)) {
                return true;
            }
        }

        return false;
    }

    // Procura indicações de link e ignora endereços de e-mail.
    public boolean contemLink(String mensagem) {
        if (contemAlgumTermo(mensagem, INDICIOS_DE_LINK)) {
            return true;
        }

        String[] palavras = mensagem.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.contains("@")) {
                continue;
            }

            String palavraSemPontuacao = palavra
                    .replace(",", "")
                    .replace(";", "")
                    .replace("!", "")
                    .replace("?", "");

            for (String dominio : DOMINIOS_DE_LINK) {
                if (palavraSemPontuacao.endsWith(dominio)
                        || palavraSemPontuacao.contains(dominio + "/")) {
                    return true;
                }
            }
        }

        return false;
    }
}
