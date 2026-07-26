import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Main {
    public static String limparTexto(String texto) {
        if (texto == null) {
            return null;
        }

        // 1. Remove acentos e caracteres não alfanuméricos (mas mantém espaços)
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}|[^\\p{Alnum}\\s]");
        String semAcentos = padrao.matcher(normalizado).replaceAll("").toLowerCase();

        // 2. Remove apenas linhas vazias (mantendo espaços dentro das frases)
        return semAcentos.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

        public static void main(String[] args) {
            System.out.println("Bem vindo ao Detector de Golpes Digitais!");
            Scanner sc = new Scanner(System.in);
            List<String> linhas = new ArrayList<>();

            System.out.println("Digite seu texto para verificar (digite apenas FIM para encerrar):");

            while (true) {
                String linha = sc.nextLine(); // lê inclusive linhas em branco
                if (linha.equals("FIM")) { // critério de parada
                    break;
                }
                linhas.add(linha);
            }

            sc.close();
            String mensagem = limparTexto(String.join("\n", linhas));
            System.out.println(mensagem);

            String[] palavrasTexto = mensagem.split(" ");
            for(String palavra : palavrasTexto) {
                System.out.println(palavra);
            }
        }
    }





