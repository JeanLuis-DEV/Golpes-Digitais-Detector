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
