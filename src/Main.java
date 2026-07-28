import modelos.DetectorGolpes;
import modelos.ResultadoAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principal: inicia o programa e conversa com o usuário pelo console.
public class Main {
    public static void main(String[] args) {

        System.out.println("Bem vindo ao Detector de Golpes Digitais!");
        Scanner scanner = new Scanner(System.in);
        List<String> linhas = new ArrayList<>();

        System.out.println("Digite seu texto para verificar (digite apenas FIM para enviar a mensagem):");

        while (true) {
            String linha = scanner.nextLine(); // lê inclusive linhas em branco
            if (linha.equals("FIM")) { // critério de parada
                break;
            }
            linhas.add(linha);
        }

        String mensagem = String.join("\n", linhas);
        System.out.println(mensagem);

        // O Scanner lê o texto digitado. O detector analisa esse texto.
        DetectorGolpes detector = new DetectorGolpes();


        // Impede que uma mensagem vazia seja enviada para análise.
        if (mensagem.trim().isEmpty()) {
            System.out.println("A mensagem não pode estar vazia.");
            scanner.close();
            return;
        }

        // Envia a mensagem para o detector e recebe o resultado.
        ResultadoAnalise resultado = detector.analisar(mensagem);
        ArrayList<String> motivos = resultado.getMotivos();

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

        // Fecha o leitor depois que ele não é mais necessário.
        scanner.close();
    }
}





