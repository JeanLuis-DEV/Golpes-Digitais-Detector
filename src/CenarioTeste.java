import Modelos.DetectorGolpes;
import Modelos.LeitorMensagemConsole;
import Modelos.ResultadoAnalise;

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
                "POSSIVELMENTE LEGÍTIMO"
        );

        executarTeste(
                detector,
                "Mensagem segura com endereço de e-mail",
                "Olá, meu e-mail para contato é atendimento@gmail.com",
                "POSSIVELMENTE LEGÍTIMO"
        );

        executarTeste(
                detector,
                "Declaração legítima de envio de PIX",
                "Parabéns pelo seu aniversário, vou enviar um pix de 120 reais.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        executarTeste(
                detector,
                "Transferência suspeita com estou te mandando",
                "Estou te mandando um pix de 50 reais para a conta do aluguel.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Pedido de foto e endereço de e-mail",
                "Bom dia\n\nVocê quer cair em um golpe?\n\n"
                        + "preciso de uma foto sua e seu email",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Prêmio com solicitação de retorno",
                "Você ganhou 100 reais, retorne nesse contato.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Valor recebido com chamada para resgate",
                "Você acaba de receber $100, me chama pra resgatar.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Promoção com pedido de valor",
                "Oi mãe, inscrevi a senhora numa promo, manda 50 reais pra receber aqui.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de valor sem informar a moeda",
                "Oi pai, to sem meu cartão aqui, manda 150 aí",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Número novo com pedido informal de valor",
                "Oi mãe, esse é meu número novo, consegue me apoiar 100 pila? meu carro quebrou",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "WhatsApp antigo perdido com pedido de valor",
                "Fala véi, perdi meu zap antigo. Salva esse aqui e quebra 80 pra mim que amanhã devolvo.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Celular quebrado com pedido de adiantamento",
                "Mãe, meu cel foi pro saco. Tô falando pelo aparelho de um parceiro, "
                        + "consegue adiantar 120?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Valor disponível com pedido de retorno",
                "Mano, saiu uma bolada no teu nome. Dá um alô aqui pra gente liberar isso.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Taxa para liberar encomenda",
                "Sua encomenda ficou travada por uma taxinha de 27,90. "
                        + "Acerta isso pra ela seguir viagem.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Troca de contato com pedido de ajuda",
                "Paizão, troca meu contato aí. Tô apertado e precisava duma força de 200 hoje.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Dinheiro aguardando retirada",
                "Tem uma grana parada te esperando aqui. Chama no zap pra pegar.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Benefício com pedido de código",
                "Consegui um benefício pra você, só falta passar os 6 dígitos "
                        + "que chegaram no celular.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Frete preso com pedido informal de valor",
                "O frete deu ruim e ficou preso. Faz a boa de 35 pra liberar ainda hoje.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Sorteio com pedido de confirmação",
                "Fui sorteado numa parada aqui e coloquei teu nome também. "
                        + "Me dá um toque pra confirmar.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Oportunidade com pagamento antecipado",
                "Amiga, apareceu uma oportunidade muito top, mas precisa entrar com 50 primeiro. Bora?",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Ameaça de bloqueio de conta",
                "Seu cadastro está irregular e sua conta será bloqueada.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Falsa central solicitando confirmação",
                "Somos da central antifraude. Confirme seus dados agora.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Cobrança antecipada para encomenda",
                "Sua encomenda está retida. Pague a taxa de entrega para liberar.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Investimento com retorno garantido",
                "Oportunidade imperdível com lucro garantido e saque imediato.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Solicitação de acesso remoto",
                "Instale AnyDesk e compartilhe sua tela com o suporte.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Prêmio em dinheiro acompanhado de link",
                "Boa tarde, dona Maria! Parabéns, a senhora acabou de ganhar 20 mil, "
                        + "clique nesse link: www.premiocerto.com.br e retire seu prêmio.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Promessa de dinheiro com link sem mencionar prêmio",
                "Receba R$ 500 acessando https://credito-liberado.com.br",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Link informativo sem promessa financeira",
                "Confira o calendário da reunião em https://empresa.com.br/agenda",
                "POSSIVELMENTE LEGÍTIMO"
        );

        // Demais cenários: diferentes exemplos de mensagens suspeitas.
        executarTeste(
                detector,
                "Mensagem com sinais de alerta",
                "Última chance! Você ganhou um prêmio.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Mensagem com vários sinais de golpe",
                "Urgente! Informe sua senha agora em https://site-desconhecido.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Mensagem de prêmio com link disfarçado",
                "Você acabou de ganhar um iphone, entre em contato agora mesmo pelo link: link.curto/premio",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de PIX para outra pessoa",
                "Mãe, preciso de um dinheiro emprestado, manda pro PIX do meu amigo meupix@pix.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Empréstimo com PIX para prestador de serviço",
                "Fala João, deu um problema com meu banco aqui, consegue me emprestar um dinheiro? "
                        + "pode enviar direto pro mecânico meupix@email.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de PIX enviado diretamente para uma loja",
                "Oi Tiago, meu cartão não está passando, consegue me mandar um pix, depois eu te pago, "
                        + "pode mandar direto para a loja pixdaloja@gmail.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Cadastro com prêmio imediato",
                "Cadastre-se e ganhe 300 reais na hora premiofacil.com/premio",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Falso vídeo em arquivo executável",
                "Olá, amigo! Peguei sua esposa lhe traindo, estou te mandando o vídeo traição.exe",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de voto com prêmio em dinheiro",
                "Oiie, vote em mim no site TheBest e ganhe 50 reais premiacao.io",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Prêmio em domínio estrangeiro",
                "Última chance! Você ganhou um prêmio em premiointernacional.co",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Mensagem com acentos usados para esconder o golpe",
                "Ólá, précisó de ajúdá, me mándá úm píx, "
                        + "mándá párá a cóntá dé úm ámígó píx@gmáíl.cóm",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTesteLeituraMultilinha(detector);

        // Novos cenários com TERMOS_VERIFICACAO_IDENTIDADE + outros termos
        executarTeste(
                detector,
                "Validação de identidade com urgência",
                "Você precisa validar sua identidade urgente para continuar usando a plataforma.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        executarTeste(
                detector,
                "Regularização de conta com pressão",
                "Sua conta está com problemas. Regularize sua conta agora mesmo para não perder acesso.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Solicitação de confirmação de dados com urgência",
                "Confirme suas informações para desbloquear sua conta urgente.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Reativação de conta com link",
                "Sua conta foi desativada. Reative sua conta clicando aqui https://www.reinicio-conta.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        // Novos cenários com TERMOS_PRESSAO_TEMPORAL_PIX + pedido de dinheiro
        executarTeste(
                detector,
                "PIX com pressão temporal + pedido de valor",
                "Mano, preciso disso hoje mesmo, nao consigo esperar. Consegue me emprestar 100 reais?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Empréstimo com urgência genuína",
                "To em apuro mesmo, nao tenho tempo pra esperar. E uma emergencia, consegue me emprestar?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de valor com urgência e confirmação rápida",
                "Preciso urgente de 150 reais. Nao posso esperar. Consegue me responder rapido?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        // Novos cenários com TERMOS_AMEACA_INFORMAL + outros termos
        executarTeste(
                detector,
                "Ameaça de bloqueio em linguagem casual com urgência",
                "Se nao conseguir fazer isso logo, vai dar ruim. Vai ficar irregular. Preciso urgente.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Ameaça de denuncia informal com transferência",
                "Voce nao sabe o apuro que estou. Se nao me ajudar, vou ter que denunciar pro banco. Manda o pix.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Ameaça com pressão psicológica e pedido",
                "Nao sabe como estou. Nao aguento mais. Se nao conseguir ajuda agora vai dar problema. Manda dinheiro.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Ameaça de deixar em nome do usuário + transferência",
                "Se voce nao me ajudar, isso vai ficar em seu registro. Vai ficar em seu nome. Manda pro meu amigo.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        // Novos cenários com TERMOS_JUSTIFICATIVA_SUSPEITA + pedido
        executarTeste(
                detector,
                "Desculpa de banco bloqueado + pedido",
                "Meu banco bloqueou minha conta, por isso nao consigo transferir. Consegue me emprestar 200?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Problema com cartão + pix",
                "Meu cartao foi cancelado e agora nao consigo sacar dinheiro. Consegue me mandar um pix de 100?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Limite excedido + urgência + pix",
                "Passei meu limite de transferencia. Manda um pix pra mim urgente que amanha devolvo.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        executarTeste(
                detector,
                "Acesso expirado + pedido de valor",
                "Meu acesso ao banco venceu, nao consigo mais transferir. Consegue me emprestar 200 reais?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        // Novos cenários com TERMOS_SOLICITACAO_CONFIRMACAO_IMEDIATA + outros
        executarTeste(
                detector,
                "Pedido de confirmação rápida + urgência",
                "Preciso saber agora, responde rapido. Consegue confirmar que recebeu? E urgente.",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        executarTeste(
                detector,
                "Pressão por resposta imediata + pix",
                "Me chama pra confirmar agora mesmo. Nao demore nao, preciso saber ja se conseguiu fazer o pix.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Pedido de confirmação com transferência",
                "Responde rapido aqui. Consegue me mandar um pix? Me confirma quando conseguir fazer.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Urgência para confirmar transferência",
                "Me avisa logo. Confirmacao se conseguiu fazer a transferencia pra mim urgente. Nao demora nao.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        // Cenários complexos combinando múltiplos termos novos + antigos
        executarTeste(
                detector,
                "Validação + Pressão temporal + Ameaça + Urgência",
                "Voce precisa validar sua identidade urgente. Se nao fizer agora vai dar problema serio. " +
                "Confirma rapido pra mim. Nao posso esperar.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Justificativa + Pressão + PIX + Urgência",
                "Meu banco bloqueou, nao consigo transferir. To em apuro mesmo, preciso de 300 reais hoje. " +
                "Responde rapido, nao aguento esperar. Consegue me ajudar?",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Ameaça + Confirmação urgente + Transferência + Terceiro",
                "Se nao fizer isso agora vai dar ruim mesmo. Me confirma logo. Manda esse pix pra mim ja pro amigo. " +
                "Nao tenho tempo.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Verificação + Limite + PIX + Urgência",
                "Voce precisa atualizar seus dados. Passei meu limite e nao consigo sacar. " +
                "Consegue me emprestar 150 reais? Preciso urgente agora.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        executarTeste(
                detector,
                "Regularização + Ameaça + Urgência + Pedido",
                "Regularize sua conta agora mesmo. Se deixar pro depois vai ficar irregular e vai dar problema. " +
                "Preciso que isso seja feito hoje. Consegue me mandar um pix? Nao demora nao.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

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

        if (!"POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)".equals(resultado.getNivelRisco())) {
            throw new AssertionError(
                    "Esperado: POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!) | Obtido: " + resultado.getNivelRisco()
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
