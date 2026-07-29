import java.util.Scanner;

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

        executarTeste(
                detector,
                "Mensagem segura com endereço de e-mail",
                "Olá, meu e-mail para contato é atendimento@gmail.com",
                "BAIXO"
        );

        executarTeste(
                detector,
                "Pedido de foto e endereço de e-mail",
                "Bom dia\n\nVocê quer cair em um golpe?\n\n"
                        + "preciso de uma foto sua e seu email",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Prêmio com solicitação de retorno",
                "Você ganhou 100 reais, retorne nesse contato.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Valor recebido com chamada para resgate",
                "Você acaba de receber $100, me chama pra resgatar.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Promoção com pedido de valor",
                "Oi mãe, inscrevi a senhora numa promo, manda 50 reais pra receber aqui.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Pedido de valor sem informar a moeda",
                "Oi pai, to sem meu cartão aqui, manda 150 aí",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Número novo com pedido informal de valor",
                "Oi mãe, esse é meu número novo, consegue me apoiar 100 pila? meu carro quebrou",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "WhatsApp antigo perdido com pedido de valor",
                "Fala véi, perdi meu zap antigo. Salva esse aqui e quebra 80 pra mim que amanhã devolvo.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Celular quebrado com pedido de adiantamento",
                "Mãe, meu cel foi pro saco. Tô falando pelo aparelho de um parceiro, "
                        + "consegue adiantar 120?",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Valor disponível com pedido de retorno",
                "Mano, saiu uma bolada no teu nome. Dá um alô aqui pra gente liberar isso.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Taxa para liberar encomenda",
                "Sua encomenda ficou travada por uma taxinha de 27,90. "
                        + "Acerta isso pra ela seguir viagem.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Troca de contato com pedido de ajuda",
                "Paizão, troca meu contato aí. Tô apertado e precisava duma força de 200 hoje.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Dinheiro aguardando retirada",
                "Tem uma grana parada te esperando aqui. Chama no zap pra pegar.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Benefício com pedido de código",
                "Consegui um benefício pra você, só falta passar os 6 dígitos "
                        + "que chegaram no celular.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Frete preso com pedido informal de valor",
                "O frete deu ruim e ficou preso. Faz a boa de 35 pra liberar ainda hoje.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Sorteio com pedido de confirmação",
                "Fui sorteado numa parada aqui e coloquei teu nome também. "
                        + "Me dá um toque pra confirmar.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Oportunidade com pagamento antecipado",
                "Amiga, apareceu uma oportunidade muito top, mas precisa entrar com 50 primeiro. Bora?",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Ameaça de bloqueio de conta",
                "Seu cadastro está irregular e sua conta será bloqueada.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Falsa central solicitando confirmação",
                "Somos da central antifraude. Confirme seus dados agora.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Cobrança antecipada para encomenda",
                "Sua encomenda está retida. Pague a taxa de entrega para liberar.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Investimento com retorno garantido",
                "Oportunidade imperdível com lucro garantido e saque imediato.",
                "MÉDIO"
        );

        executarTeste(
                detector,
                "Solicitação de acesso remoto",
                "Instale AnyDesk e compartilhe sua tela com o suporte.",
                "ALTO"
        );

        executarTeste(
                detector,
                "Prêmio em dinheiro acompanhado de link",
                "Boa tarde, dona Maria! Parabéns, a senhora acabou de ganhar 20 mil, "
                        + "clique nesse link: www.premiocerto.com.br e retire seu prêmio.",
                "ALTO"
        );

        executarTeste(
                detector,
                "Promessa de dinheiro com link sem mencionar prêmio",
                "Receba R$ 500 acessando https://credito-liberado.com.br",
                "ALTO"
        );

        executarTeste(
                detector,
                "Link informativo sem promessa financeira",
                "Confira o calendário da reunião em https://empresa.com.br/agenda",
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

        executarTeste(
                detector,
                "Mensagem com acentos usados para esconder o golpe",
                "Ólá, précisó de ajúdá, me mándá úm píx, "
                        + "mándá párá a cóntá dé úm ámígó píx@gmáíl.cóm",
                "ALTO"
        );

        executarTesteLeituraMultilinha(detector);

        System.out.println("Todos os cenários passaram.");
    }

    // Confere a leitura de mensagens coladas com várias linhas e espaços em branco.
    private static void executarTesteLeituraMultilinha(DetectorGolpes detector) {
        String entrada = "Hoje é terça, tenha um ótimo dia!\n"
                + "\n"
                + "Clique nesse link e envie seus dados bancários com a senha para ganhar 300 reais\n"
                + "\n"
                + "link: www.linksuspeito.com.br\n"
                + "FIM\n";

        Scanner scanner = new Scanner(entrada);
        LeitorMensagemConsole leitor = new LeitorMensagemConsole();
        String mensagem = leitor.ler(scanner);
        scanner.close();

        ResultadoAnalise resultado = detector.analisar(mensagem);
        System.out.println("Mensagem colada com várias linhas: " + resultado.getNivelRisco());

        if (!"ALTO".equals(resultado.getNivelRisco())) {
            throw new AssertionError(
                    "Esperado: ALTO | Obtido: " + resultado.getNivelRisco()
            );
        }
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
