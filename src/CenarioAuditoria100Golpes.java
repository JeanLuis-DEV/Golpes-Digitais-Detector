import Modelos.DetectorGolpes;
import Modelos.ResultadoAnalise;

import java.util.List;

/**
 * Massa independente com mensagens fraudulentas para medir falsos negativos.
 */
public class CenarioAuditoria100Golpes {

    private static final String RISCO_ALTO =
            "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)";

    private static final List<String> MENSAGENS_DE_GOLPE = List.of(
            "Sou do banco e preciso da sua senha para cancelar uma compra.",
            "Me diga o código de seis números que acabou de chegar no celular.",
            "Para liberar sua conta, envie agência, conta e CPF.",
            "Confirme o número completo do cartão e a validade para continuarmos.",
            "Passe o token que aparece no aplicativo bancário.",
            "Fotografe seu documento e mande uma selfie segurando ele.",
            "Preciso do PIN do seu cartão para concluir o estorno.",
            "Informe a resposta da pergunta secreta da sua conta.",
            "Manda um print da tela inicial do banco mostrando o saldo.",
            "Digite aqui o usuário e a senha usados no internet banking.",

            "Oi mãe, troquei de número e preciso de um PIX para pagar o aluguel.",
            "Pai, meu celular quebrou; transfere 850 para esta chave nova.",
            "Amor, estou sem acesso à conta, paga esse boleto por mim.",
            "Sou seu primo e fui assaltado, deposita dinheiro nesta conta.",
            "Chefe falando: compre cartões-presente e mande os códigos agora.",
            "Fiz um PIX errado para você, devolva para esta outra chave.",
            "Meu limite acabou, manda cem reais que amanhã eu devolvo.",
            "Estou no hospital e preciso que pague esta cobrança imediatamente.",
            "Seu filho foi detido; faça uma transferência para liberar ele.",
            "Não avise ninguém, só envie o dinheiro e depois explico tudo.",

            "Sua conta tem uma pendência, entre em banc0-seguro.net para regularizar.",
            "Acesse http://atualizacao-cadastral.xyz e evite o bloqueio.",
            "Confira a compra desconhecida em www.cartao-alerta.top.",
            "Clique no link encurtado bit.ly/conta-segura para confirmar sua identidade.",
            "O banco exige recadastramento em seguranca-nubank.digital.",
            "Entre em suporte-caixa.online e valide seus dados hoje.",
            "Seu e-mail será encerrado; reative em correio-verificacao.click.",
            "Veja a intimação anexada em justica-processo.info/documento.",
            "Atualize o WhatsApp pelo endereço whatsapp-premium.download.",
            "A promoção está em mercadolivre-oferta.shop/pagamento.",

            "Parabéns, você ganhou um carro; pague o frete para receber.",
            "Seu CPF foi sorteado e há vinte mil reais esperando por você.",
            "Resgate agora o benefício esquecido antes que ele expire.",
            "Você recebeu cashback de 900 reais, confirme para sacar.",
            "Há uma restituição disponível, pague a tarifa de liberação.",
            "Seu cupom foi premiado; deposite 50 reais para validar.",
            "Ganhou uma viagem completa, basta pagar o seguro da reserva.",
            "O governo liberou um auxílio extra, faça o cadastro por aqui.",
            "Você foi contemplado, mande seus documentos para retirar o prêmio.",
            "Crédito pré-aprovado sem consulta; antecipe a taxa do contrato.",

            "Instale o Supremo para nosso técnico corrigir seu aplicativo.",
            "Baixe o RustDesk e me passe o número exibido na tela.",
            "Abra o QuickSupport para eu remover o vírus do seu telefone.",
            "Ative o compartilhamento de tela enquanto abre sua conta bancária.",
            "Permita que eu controle seu computador para fazer o estorno.",
            "Instale este APK de segurança enviado pelo atendimento.",
            "Execute o arquivo protecao.scr para desbloquear o computador.",
            "Abra a planilha premio.xlsm e habilite todo o conteúdo.",
            "Desative o antivírus antes de instalar nossa ferramenta.",
            "Conecte seu aparelho ao suporte remoto e não toque em nada.",

            "Sua encomenda ficou retida; pague 6,90 para liberar a entrega.",
            "Os Correios precisam de uma tarifa para entregar seu pacote hoje.",
            "Há imposto alfandegário pendente na sua compra internacional.",
            "O entregador não encontrou o endereço, confirme pagando a nova tentativa.",
            "Seu pedido será devolvido se a taxa logística não for quitada.",
            "Pague o despacho postal via PIX para receber a mercadoria.",
            "A transportadora cobra seguro obrigatório antes da entrega.",
            "Seu pacote está no depósito; compre um código de retirada.",
            "Regularize a etiqueta da encomenda fazendo este pagamento.",
            "Existe uma multa na entrega e só aceitamos transferência.",

            "Invista 500 e receba 5 mil em apenas uma semana.",
            "Nosso robô de cripto nunca perde e garante lucro diário.",
            "Dobre seu capital hoje com sinais secretos do mercado.",
            "Entre no grupo VIP e copie operações com retorno garantido.",
            "Essa moeda vai subir 1.000%, compre antes do anúncio.",
            "Deposite na corretora privada e saque rendimentos amanhã.",
            "Renda passiva automática sem risco e sem precisar trabalhar.",
            "O gerente consegue multiplicar seu dinheiro usando uma conta especial.",
            "Faça um empréstimo para aproveitar esta oportunidade única.",
            "Transfira suas criptos para esta carteira e receba o dobro.",

            "Você foi contratado, mas precisa pagar o uniforme antes de começar.",
            "Vaga home office: compre nosso treinamento para liberar o trabalho.",
            "Ganhe 800 por dia curtindo vídeos após fazer um pequeno depósito.",
            "A empresa exige taxa de cadastro para enviar o equipamento.",
            "Faça tarefas simples e recarregue a plataforma para sacar comissão.",
            "Seu currículo foi aprovado; pague o exame admissional nesta chave.",
            "Trabalho garantido, envie foto dos documentos e dados da conta.",
            "Para ativar seu salário, faça primeiro uma transferência de teste.",
            "Somos do RH, compre o material do fornecedor indicado.",
            "Receba por avaliar produtos, basta adicionar saldo à sua carteira.",

            "Tenho suas fotos íntimas; pague para eu não enviar aos seus contatos.",
            "Se não transferir agora, vou divulgar tudo sobre você.",
            "Sou médico no exterior e preciso de dinheiro para liberar minha bagagem.",
            "Me apaixonei por você, mas preciso pagar uma passagem para te encontrar.",
            "Um parente seu sofreu acidente; faça o depósito e não telefone para ele.",
            "Estamos com seu familiar; mande dinheiro e não chame a polícia.",
            "Sou policial e seu nome apareceu numa investigação; pague para encerrar.",
            "O advogado consegue retirar seu processo do sistema mediante depósito.",
            "Há um mandado contra você, resolva discretamente com esta transferência.",
            "Seu computador foi filmado; compre bitcoin para evitar a divulgação.",

            "O comprador pagou a mais; devolva a diferença para a conta do transportador.",
            "Para receber pela venda, informe o código que chegou no seu e-mail.",
            "O pagamento está preso; faça um PIX para aumentar o limite da carteira.",
            "Escaneie este QR Code para confirmar o recebimento da sua venda.",
            "O boleto venceu, use esta segunda via com beneficiário atualizado.",
            "Compre gift cards e envie foto dos códigos para quitar a dívida.",
            "A maquininha apresentou erro; aproxime o cartão novamente sem olhar o valor.",
            "Sou comprador da OLX, confirme a venda pelo formulário que enviei.",
            "O suporte do marketplace pede um depósito caução para liberar o saldo.",
            "A falsa cobrança será cancelada depois que você fizer uma transferência de verificação."
    );

    public static void main(String[] args) {
        if (MENSAGENS_DE_GOLPE.size() != 100) {
            throw new AssertionError(
                    "A auditoria deve conter exatamente 100 mensagens."
            );
        }

        DetectorGolpes detector = new DetectorGolpes();
        int riscoAlto = 0;
        int suspeito = 0;
        int possivelmenteLegitimo = 0;

        for (int indice = 0; indice < MENSAGENS_DE_GOLPE.size(); indice++) {
            String mensagem = MENSAGENS_DE_GOLPE.get(indice);
            ResultadoAnalise resultado = detector.analisar(mensagem);

            if (RISCO_ALTO.equals(resultado.nivelRisco())) {
                riscoAlto++;
                continue;
            }

            if ("SUSPEITO(VERIFIQUE A FONTE)".equals(
                    resultado.nivelRisco()
            )) {
                suspeito++;
            } else {
                possivelmenteLegitimo++;
            }

            System.out.printf(
                    "%03d | %s | %d pontos | %s%n",
                    indice + 1,
                    resultado.nivelRisco(),
                    resultado.pontuacao(),
                    mensagem
            );
        }

        System.out.println();
        System.out.println("Risco alto: " + riscoAlto);
        System.out.println("Suspeito: " + suspeito);
        System.out.println(
                "Possivelmente legítimo: " + possivelmenteLegitimo
        );

        if (riscoAlto != MENSAGENS_DE_GOLPE.size()) {
            throw new AssertionError(
                    "Todas as 100 mensagens fraudulentas devem ser classificadas como risco alto."
            );
        }
    }
}
