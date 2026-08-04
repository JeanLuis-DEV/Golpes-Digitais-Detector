package Modelos;

import java.util.Scanner;

/**
 * Controla toda a interação pelo terminal.
 * <p>
 * A classe reúne:
 * - apresentação inicial;
 * - leitura da mensagem;
 * - exibição do resultado.
 */
public final class ConsoleApp {

    private static final String COMANDO_FINALIZAR = "FIM";
    private static final int LARGURA = 66;

    private final DetectorGolpes detector =
            new DetectorGolpes();

    public void executar() {
        try (Scanner scanner = new Scanner(System.in)) {
            exibirPainelInicial();

            String mensagem = lerMensagem(scanner);

            if (mensagem.isBlank()) {
                System.out.println(
                        "A mensagem não pode estar vazia."
                );
                return;
            }

            ResultadoAnalise resultado =
                    detector.analisar(mensagem);

            exibirResultado(resultado);
        }
    }

    private String lerMensagem(Scanner scanner) {
        StringBuilder mensagem = new StringBuilder();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();

            if (COMANDO_FINALIZAR.equalsIgnoreCase(
                    linha.trim()
            )) {
                break;
            }

            if (!mensagem.isEmpty()) {
                mensagem.append(System.lineSeparator());
            }

            mensagem.append(linha);
        }

        return mensagem.toString();
    }

    private void exibirResultado(
            ResultadoAnalise resultado
    ) {
        System.out.println();
        System.out.println(
                "Nível de risco: " + resultado.nivelRisco()
        );
        System.out.println(
                "Pontuação: " + resultado.pontuacao()
        );

        if (resultado.motivos().isEmpty()) {
            System.out.println(
                    "Nenhum sinal de golpe foi encontrado."
            );
            return;
        }

        System.out.println("Sinais encontrados:");

        for (String motivo : resultado.motivos()) {
            System.out.println("- " + motivo);
        }
    }

    private void exibirPainelInicial() {
        String bordaPrincipal =
                "=".repeat(LARGURA);

        String bordaInterna =
                "-".repeat(LARGURA);

        System.out.println();
        System.out.println(
                "+" + bordaPrincipal + "+"
        );

        exibirLinhaVazia();

        exibirLinhaCentralizada(
                "DETECTOR DE GOLPES DIGITAIS"
        );

        exibirLinhaCentralizada(
                "Analise mensagens e identifique sinais de fraude"
        );

        exibirLinhaVazia();

        System.out.println(
                "+" + bordaInterna + "+"
        );

        exibirLinhaVazia();

        exibirLinhaCentralizada(
                "COLE OU DIGITE A MENSAGEM ABAIXO"
        );

        exibirLinhaCentralizada(
                "A mensagem pode conter uma ou várias linhas."
        );

        exibirLinhaVazia();

        exibirLinhaCentralizada(
                "Para finalizar, digite FIM em uma nova linha."
        );

        exibirLinhaVazia();

        System.out.println(
                "+" + bordaPrincipal + "+"
        );

        System.out.println();
    }

    private void exibirLinhaCentralizada(String texto) {
        int espacosDisponiveis =
                Math.max(0, LARGURA - texto.length());

        int espacosEsquerda =
                espacosDisponiveis / 2;

        int espacosDireita =
                espacosDisponiveis - espacosEsquerda;

        System.out.println(
                "|"
                        + " ".repeat(espacosEsquerda)
                        + texto
                        + " ".repeat(espacosDireita)
                        + "|"
        );
    }

    private void exibirLinhaVazia() {
        System.out.println(
                "|" + " ".repeat(LARGURA) + "|"
        );
    }
}