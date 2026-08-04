import Modelos.DetectorGolpes;
import Modelos.InterfaceConsole;
import Modelos.LeitorMensagemConsole;
import Modelos.ResultadoAnalise;

import java.util.List;
import java.util.Scanner;

// Classe principal: inicia o programa e conversa com o usuário pelo console.
public class Main {
    public static void main(String[] args) {
        // O Scanner lê o texto digitado. O leitor reúne todas as linhas da mensagem.
        try (Scanner scanner = new Scanner(System.in)) {
            InterfaceConsole interfaceConsole = new InterfaceConsole();
            DetectorGolpes detector = new DetectorGolpes();
            // Exibe a apresentação e solicita a mensagem.
            interfaceConsole.exibirPainelInicial();
            String mensagem = LeitorMensagemConsole.ler(scanner);
            // Impede que uma mensagem vazia seja enviada para análise.
            if (mensagem.isBlank()) {
                System.out.println("A mensagem não pode estar vazia.");
                return;
            }

            // Envia a mensagem para o detector e recebe o resultado.
            ResultadoAnalise resultado = detector.analisar(mensagem);
            List<String> motivos = resultado.getMotivos();

            // Mostra a classificação e a pontuação calculadas.
            System.out.println();
            System.out.println("Nível de risco: " + resultado.getNivelRisco());
            System.out.println("Pontuação: " + resultado.getPontuacao());

            // Mostra os sinais encontrados ou informa que nenhum sinal foi localizado.
            if (motivos.isEmpty()) {
                System.out.println("Nenhum sinal de golpe foi encontrado.");
            } else {
                System.out.println("Sinais encontrados:");

                for (String motivo : motivos) {
                    System.out.println("- " + motivo);
                }
            }
        }
    }
}
