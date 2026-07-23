import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Main {
    public static String limparTexto(String texto) {
        if (texto == null) {
            return null;
        }

        // 1. Decompõe os caracteres acentuados em caractere base + acento
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);

        // 2. Expressão regular:
        // \\p{InCombiningDiacriticalMarks} remove os acentos (diacríticos)
        // [^\p{Alnum}\s] remove tudo que não é letra
        Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}|[^\\p{Alnum}\\s]");

        return padrao.matcher(normalizado).replaceAll("");
    }

        public static void main(String[] args) {
            System.out.println("Bem vindo ao Detector de Golpes Digitais!");
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a mensagem para verificar: ");
            String mensagem = sc.nextLine();
            System.out.println(limparTexto(mensagem));
        }
    }





