// Organiza a apresentação visual do programa no terminal.
public class InterfaceConsole {
    private static final int LARGURA_CONTEUDO = 66;
    private static final String BORDA_PRINCIPAL = "=".repeat(LARGURA_CONTEUDO);
    private static final String BORDA_INTERNA = "-".repeat(LARGURA_CONTEUDO);

    public void exibirPainelInicial() {
        System.out.println();
        System.out.println("+" + BORDA_PRINCIPAL + "+");
        exibirLinhaVazia();
        exibirLinhaCentralizada("DETECTOR DE GOLPES DIGITAIS");
        exibirLinhaCentralizada("Analise mensagens e identifique sinais de fraude");
        exibirLinhaVazia();
        System.out.println("+" + BORDA_INTERNA + "+");
        exibirLinhaVazia();
        exibirLinhaCentralizada("COLE OU DIGITE A MENSAGEM ABAIXO");
        exibirLinhaCentralizada("A mensagem pode conter uma ou várias linhas.");
        exibirLinhaVazia();
        exibirLinhaCentralizada("Para finalizar, digite FIM em uma nova linha.");
        exibirLinhaVazia();
        System.out.println("+" + BORDA_PRINCIPAL + "+");
        System.out.println();
    }

    private void exibirLinhaCentralizada(String texto) {
        int espacosDisponiveis = LARGURA_CONTEUDO - texto.length();
        int espacosEsquerda = espacosDisponiveis / 2;
        int espacosDireita = espacosDisponiveis - espacosEsquerda;

        System.out.println(
                "|"
                        + " ".repeat(espacosEsquerda)
                        + texto
                        + " ".repeat(espacosDireita)
                        + "|"
        );
    }

    private void exibirLinhaVazia() {
        System.out.println("|" + " ".repeat(LARGURA_CONTEUDO) + "|");
    }
}
