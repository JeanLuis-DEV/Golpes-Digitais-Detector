// Executa cenários conhecidos para conferir se as regras continuam funcionando.
public class CenarioTeste {
    public static void main(String[] args) {
        // O mesmo detector é reutilizado em todos os cenários.
        DetectorGolpes detector = new DetectorGolpes();

        // Primeiro cenário: uma conversa normal deve apresentar risco baixo.
        executarTeste(
                detector,
                "Mensagem segura",
                "Olá, a reunião será amanhã às 14 horas.",
                "BAIXO"
        );

        // Demais cenários: diferentes exemplos de mensagens suspeitas.
        executarTeste(
                detector,
                "Mensagem com sinais de alerta",
                "Última chance! Você ganhou um prêmio.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Mensagem com vários sinais de golpe",
                "Urgente! Informe sua senha agora em https://site-desconhecido.com",
                "ALTO"
        );

        executarTeste(
                detector,
                "Mensagem de prêmio com link disfarçado",
                "Você acabou de ganhar um iphone, entre em contato agora mesmo pelo link: link.curto/premio",
                "ALTO"
        );

        executarTeste(
                detector,
                "Pedido de PIX para outra pessoa",
                "Mãe, preciso de um dinheiro emprestado, manda pro PIX do meu amigo meupix@pix.com",
                "ALTO"
        );

        executarTeste(
                detector,
                "Empréstimo com PIX para prestador de serviço",
                "Fala João, deu um problema com meu banco aqui, consegue me emprestar um dinheiro? "
                        + "pode enviar direto pro mecânico meupix@email.com",
                "ALTO"
        );

        executarTeste(
                detector,
                "Pedido de PIX enviado diretamente para uma loja",
                "Oi Tiago, meu cartão não está passando, consegue me mandar um pix, depois eu te pago, "
                        + "pode mandar direto para a loja pixdaloja@gmail.com",
                "ALTO"
        );

        executarTeste(
                detector,
                "Cadastro com prêmio imediato",
                "Cadastre-se e ganhe 300 reais na hora premiofacil.com/premio",
                "ALTO"
        );

        executarTeste(
                detector,
                "Falso vídeo em arquivo executável",
                "Olá, amigo! Peguei sua esposa lhe traindo, estou te mandando o vídeo traição.exe",
                "ALTO"
        );

        executarTeste(
                detector,
                "Pedido de voto com prêmio em dinheiro",
                "Oiie, vote em mim no site TheBest e ganhe 50 reais premiacao.io",
                "ALTO"
        );

        executarTeste(
                detector,
                "Prêmio em domínio estrangeiro",
                "Última chance! Você ganhou um prêmio em premiointernacional.co",
                "ALTO"
        );

        System.out.println("Todos os cenários passaram.");
    }

    // Analisa uma mensagem e compara o resultado obtido com o resultado esperado.
    private static void executarTeste(
            DetectorGolpes detector,
            String nomeDoCenario,
            String mensagem,
            String resultadoEsperado
    ) {
        ResultadoAnalise resultado = detector.analisar(mensagem);
        String resultadoObtido = resultado.getNivelRisco();

        System.out.println(nomeDoCenario + ": " + resultadoObtido);

        // Interrompe o teste e informa o erro caso a classificação esteja incorreta.
        if (!resultadoEsperado.equals(resultadoObtido)) {
            throw new AssertionError(
                    "Esperado: " + resultadoEsperado + " | Obtido: " + resultadoObtido
            );
        }
    }
}
