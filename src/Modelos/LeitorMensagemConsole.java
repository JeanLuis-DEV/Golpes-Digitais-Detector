package modelos;

import java.util.Scanner;

// Lê mensagens com uma ou várias linhas pelo terminal.
public final class LeitorMensagemConsole {
    private static final String COMANDO_FINALIZAR = "FIM";

    public String ler(Scanner scanner) {
        StringBuilder mensagem = new StringBuilder();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();

            if (COMANDO_FINALIZAR.equalsIgnoreCase(linha.trim())) {
                break;
            }

            if (mensagem.length() > 0) {
                mensagem.append(System.lineSeparator());
            }

            mensagem.append(linha);
        }

        return mensagem.toString();
    }
}
