import Modelos.DetectorGolpes;
import Modelos.ResultadoAnalise;

/**
 * Executa testes básicos sem depender de bibliotecas externas.
 */
public class CenarioTeste {

    public static void main(String[] args) {
        DetectorGolpes detector = new DetectorGolpes();

        testar(
                detector,
                "Mensagem comum",
                "Olá, a reunião será amanhã às 14 horas.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "E-mail comum",
                "Olá, meu e-mail é atendimento@gmail.com",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Palavra real dentro de palavra maior",
                "Este documento é realmente importante.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Extensão parecida com arquivo perigoso",
                "Veja as fotos no arquivo.jardim",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Envio legítimo de PIX",
                "Parabéns pelo aniversário, vou enviar um pix de 120 reais.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Senha e link com urgência",
                "Urgente! Informe sua senha agora em https://site-desconhecido.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Falso advogado solicitando dados bancários",
                "Aqui é teu advogado, eu preciso que tu passe seus dados do banco para conseguir acessar ele.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Solicitação direta de credenciais bancárias",
                "Informe seu login e senha para acessar a conta.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Solicitação de código de autenticação",
                "Passe o código que chegou para concluir o atendimento.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Golpe híbrido em português e inglês",
                "Olá, sou o Gerente, passe o number do seu card e o security code",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Golpe híbrido com erros ortográficos",
                "Sou o dono do banco, precido do card number para ferificar aqui seu saldo",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Falsa gerente solicitando cartão com diminutivos",
                "Olá João, aqui é sua gerente, manda uma fotinho frente e verso do cartãozinho para eu te ajudar",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Diminutivo de cartão em contexto legítimo",
                "O cartãozinho de aniversário ficou bonito.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Pedido legítimo de fotografia",
                "Manda uma fotinho do cachorro quando puder.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Falsa vaga solicitando imagens faciais",
                "Oiie, tudo bem? tenho uma vaga de emprego para você, envie uma foto do seu rosto de frente e dos dois lados para eu te cadastrar aqui na entrevista",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Menção legítima a fotografia de rosto",
                "A foto do rosto ficou boa para o álbum da família.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Pedido legítimo de fotografia sem dado facial",
                "Envie uma foto da fachada para a entrevista sobre arquitetura.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Cadastro legítimo para emissão de nota fiscal",
                "Oi, como você havia solicitado, estou te mandando o valor combinado, para eu gerar a nota fiscal, me manda seus dados pessoais para cadastro",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        testar(
                detector,
                "Nota fiscal legítima de valor combinado",
                "A nota fiscal do valor combinado segue em anexo.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Nota fiscal usada como pretexto para cobrança",
                "Para gerar a nota fiscal do cadastro, pague a taxa de liberação agora.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Descrição técnica de número de cartão",
                "O campo card number possui dezesseis dígitos no formulário de teste.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Golpe em inglês com dados do cartão",
                "I am the bank manager, send your card number and security code.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Golpe em espanhol com dados do cartão",
                "Soy el gerente del banco, comparte el número de tu tarjeta y el código de seguridad.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Golpe em francês com dados do cartão",
                "Je suis le conseiller bancaire, donnez votre numéro de carte et le code de sécurité.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Golpe híbrido com falso suporte",
                "Informe o OTP para o support agent concluir a validação.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Card como componente visual",
                "Envie o card do componente para revisão visual.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Card em contexto de design",
                "Share the card layout with the design team.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Orientação de segurança em inglês",
                "Never share your password or security code with anyone.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Orientação de segurança em espanhol",
                "Nunca compartas tu contraseña ni el código de seguridad.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Orientação de segurança em francês",
                "Ne partagez jamais votre mot de passe ni votre code de sécurité.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Orientação para não compartilhar dados bancários",
                "Nunca passe seus dados do banco por mensagem.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Menção legítima a advogado",
                "Meu advogado enviou os dados do processo para análise.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Cartão bloqueado com solicitação de imagem",
                "Seu cartão foi bloqueado, envie a foto frente e verso do cartão.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Alerta de cartão com orientação para canal oficial",
                "Seu cartão foi bloqueado por segurança, entre em contato com o nosso banco pelo canal oficial do site.",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Solicitação de dados sensíveis do cartão",
                "Mande o CVV do cartão e uma foto do verso para validação.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Acesso remoto",
                "Instale AnyDesk e compartilhe sua tela com o suporte.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Acesso remoto por QuickSupport",
                "Instale QuickSupport e informe o código de acesso remoto.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Número novo com pedido de PIX",
                "Mãe, esse é meu número novo, manda um pix de 100 reais.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Falsa central",
                "Somos da central antifraude. Confirme seus dados agora.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Prêmio com link",
                "Você ganhou 500 reais. Acesse https://premio-facil.com",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Link de comércio oficial",
                "Confira o pedido em https://ofertas.mercadolivre.com.br/minha-conta",
                "POSSIVELMENTE LEGÍTIMO"
        );

        testar(
                detector,
                "Link desconhecido",
                "Acesse https://loja-nova-exemplo.com/oferta",
                "SUSPEITO(VERIFIQUE A FONTE)"
        );

        testar(
                detector,
                "Domínio que imita comércio conhecido",
                "Acesse https://www.mercad0livre.com/oferta para acompanhar seu pedido.",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Falso subdomínio de comércio oficial",
                "Acesse https://mercadolivre.com.br.golpe.com/oferta",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testar(
                detector,
                "Arquivo executável",
                "Estou te enviando as fotos no arquivo imagens.exe",
                "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)"
        );

        testarMensagemVazia(detector);

        System.out.println();
        System.out.println("Todos os cenários passaram.");
    }

    private static void testar(
            DetectorGolpes detector,
            String nome,
            String mensagem,
            String resultadoEsperado
    ) {
        ResultadoAnalise resultado =
                detector.analisar(mensagem);

        System.out.println(
                nome
                        + ": "
                        + resultado.nivelRisco()
                        + " | Pontuação: "
                        + resultado.pontuacao()
        );

        if (!resultadoEsperado.equals(
                resultado.nivelRisco()
        )) {
            throw new AssertionError(
                    "Teste: " + nome
                            + System.lineSeparator()
                            + "Esperado: "
                            + resultadoEsperado
                            + System.lineSeparator()
                            + "Obtido: "
                            + resultado.nivelRisco()
                            + System.lineSeparator()
                            + "Pontuação: "
                            + resultado.pontuacao()
                            + System.lineSeparator()
                            + "Motivos: "
                            + resultado.motivos()
            );
        }
    }

    private static void testarMensagemVazia(
            DetectorGolpes detector
    ) {
        try {
            detector.analisar("   ");

            throw new AssertionError(
                    "Era esperada uma IllegalArgumentException."
            );
        } catch (IllegalArgumentException excecao) {
            String mensagemEsperada =
                    "A mensagem não pode estar vazia.";

            if (!mensagemEsperada.equals(
                    excecao.getMessage()
            )) {
                throw new AssertionError(
                        "Mensagem de validação inesperada: "
                                + excecao.getMessage()
                );
            }
        }
    }
}
