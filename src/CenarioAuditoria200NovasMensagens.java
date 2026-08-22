import Modelos.DetectorGolpes;
import Modelos.ResultadoAnalise;

import java.util.List;

/**
 * Segunda massa independente: 100 golpes e 100 mensagens suspeitas.
 */
public class CenarioAuditoria200NovasMensagens {

    private static final String GOLPE =
            "POSSIVELMENTE GOLPE(TENHA MUITO CUIDADO!)";
    private static final String SUSPEITO =
            "SUSPEITO(VERIFIQUE A FONTE)";
    private static final String LEGITIMO =
            "POSSIVELMENTE LEGÍTIMO";

    private static final List<String> GOLPES = List.of(
            "Aqui é da segurança do cartão, fale os três números do verso.",
            "Para cancelar a TED, preciso que confirme sua senha eletrônica.",
            "Copie para mim a chave de recuperação exibida no aplicativo.",
            "Envie uma foto do seu cartão ao lado do documento de identidade.",
            "Diga qual código apareceu na notificação para aprovarmos o bloqueio.",
            "Sua conta será protegida depois que você informar o PIN.",
            "Compartilhe a tela do banco e mostre as últimas movimentações.",
            "Passe o número do benefício e sua data de nascimento para liberar o saque.",
            "Preciso da biometria facial por vídeo para validar o atendimento.",
            "Responda com agência, conta, CPF e nome da mãe.",

            "Vó, estou usando o telefone de um amigo; manda dinheiro para esta chave.",
            "Sou o diretor da empresa, faça este pagamento com absoluto sigilo.",
            "O fornecedor mudou os dados bancários, liquide a nota na conta nova.",
            "Sua irmã sofreu um acidente e precisa de depósito para a cirurgia.",
            "Estamos negociando a libertação dele, não envolva autoridades.",
            "Seu marido pediu que você compre vouchers e envie as numerações.",
            "Meu telefone caiu na água, salva este contato e paga a fatura urgente.",
            "Sou do cartório e existe uma ordem que só pode ser suspensa mediante PIX.",
            "O gerente autorizou uma conta de passagem; transfira o saldo inteiro.",
            "Faça uma transferência teste para provar que a conta pertence a você.",

            "Regularize seu cadastro em https://portal-seguro-brasil.xyz/cliente.",
            "Existe uma multa no seu CPF em receita-consulta.top/pagamento.",
            "Sua assinatura expirou, entre em netflix-renovacao.click agora.",
            "O comprovante está disponível em https://documento-fiscal.download/nota.",
            "Abra correios-rastreio.digital e pague a pendência da encomenda.",
            "Valide o dispositivo em itau-protecao.online antes do bloqueio.",
            "Seu perfil foi denunciado; recupere em instagram-ajuda.site.",
            "A fatura corrigida está em energia-segundavia.shop/arquivo.",
            "Confirme a reserva pelo endereço booking-hospede.live/checkin.",
            "Atualize seus dados em govbr-beneficio.info para não perder o pagamento.",

            "Aplique dois mil hoje e retire vinte mil no fim do mês.",
            "Esta carteira de moedas digitais paga rendimento fixo de 30% ao dia.",
            "Nosso especialista opera por você e não existe chance de prejuízo.",
            "Venda seu carro e coloque tudo neste fundo exclusivo antes que feche.",
            "A plataforma libera o saque após uma última recarga de segurança.",
            "Compre esta criptomoeda secreta antes que os bancos descubram.",
            "O algoritmo transforma cem reais em dez mil automaticamente.",
            "Pegue crédito no banco e participe desta rodada com retorno certo.",
            "Envie USDT para esta carteira e desbloqueie o rendimento acumulado.",
            "Você só precisa indicar amigos e fazer um aporte para ganhar todo dia.",

            "Seu primeiro dia está confirmado após o pagamento do kit de trabalho.",
            "Para receber as tarefas, faça uma recarga inicial de cinquenta reais.",
            "A vaga é sua, compre o certificado obrigatório com nosso parceiro.",
            "Pague a reserva do computador que será enviado para seu home office.",
            "O recrutador solicita um depósito reembolsável para confirmar a entrevista.",
            "Avalie hotéis pela internet e deposite saldo para liberar comissões.",
            "Sua contratação depende da compra imediata do curso de integração.",
            "Ganhe por digitar notas fiscais, mas ative a conta com um pagamento.",
            "O RH precisa que você pague a consulta médica antes da admissão.",
            "Faça pedidos simulados com seu dinheiro e receba comissão dobrada.",

            "A entrega do seu celular depende do pagamento de uma tarifa municipal.",
            "Seu pacote foi apreendido e será leiloado se não houver depósito hoje.",
            "A transportadora exige PIX para corrigir o CEP do destinatário.",
            "Pague a armazenagem para retirar a encomenda supostamente internacional.",
            "O motoboy aguarda a taxa de reenvio antes de sair para entrega.",
            "Existe diferença de imposto; quite pelo QR enviado nesta mensagem.",
            "Sua compra está parada na fiscalização, transfira o valor solicitado.",
            "Confirme o endereço comprando um selo digital de entrega.",
            "O pacote surpresa requer pagamento antecipado da alfândega.",
            "A encomenda gratuita será entregue depois da taxa de manuseio.",

            "Instale o aplicativo enviado fora da loja para remover a invasão.",
            "Abra o programa de assistência e aceite todas as permissões.",
            "Desligue a proteção do celular para nosso técnico acessar o banco.",
            "Execute seguranca.bat e aguarde enquanto corrigimos sua conta.",
            "Habilite as macros do documento para visualizar a cobrança.",
            "Informe o identificador exibido no TeamViewer ao atendente.",
            "Aceite o controle do mouse para cancelarmos a transferência.",
            "Faça login no banco enquanto o espelhamento de tela estiver ativo.",
            "Instale o certificado recebido por mensagem e ignore o alerta do sistema.",
            "Baixe o arquivo comprovante.apk para consultar o pagamento.",

            "O cliente enviou valor excedente, repasse a sobra ao motorista.",
            "Para desbloquear o dinheiro da venda, pague uma tarifa ao intermediador.",
            "A plataforma exige os dados do cartão para depositar o valor vendido.",
            "Leia o QR recebido no chat para aceitar a proposta do comprador.",
            "O comprador mandou um e-mail; copie o código dele para confirmar o anúncio.",
            "Troque o boleto do fornecedor por este com os dados corrigidos.",
            "A venda só termina depois que você comprar um vale-presente.",
            "Pague o seguro da transação para receber o saldo do marketplace.",
            "Aproxime novamente e digite a senha, pois a primeira tentativa falhou.",
            "O falso suporte pede uma transferência para verificar sua carteira digital.",

            "Gravei você pela câmera; envie criptomoedas para apagar o vídeo.",
            "Se não depositar, seus documentos serão publicados na internet.",
            "Seu neto está sob nosso controle, não tente confirmar com a família.",
            "Sou investigador e posso arquivar a denúncia após um pagamento reservado.",
            "Há conteúdo comprometedor no seu telefone; compre cartões para evitar vazamento.",
            "Uma pessoa que conheceu online precisa de dinheiro para sair da imigração.",
            "O suposto militar precisa de ajuda financeira para voltar ao país.",
            "Pague imediatamente ou todos os seus contatos receberão as imagens.",
            "Um agente judicial oferece cancelar a prisão mediante transferência.",
            "Seu familiar foi sequestrado; permaneça na linha e faça o PIX.",

            "Seu imposto tem restituição milionária, antecipe os honorários para receber.",
            "O INSS aprovou valores atrasados após pagamento da taxa processual.",
            "Você ganhou um smartphone e deve pagar apenas o seguro de envio.",
            "Seu empréstimo foi aprovado, deposite a caução antes da liberação.",
            "Há herança em seu nome; envie dinheiro para autenticar os documentos.",
            "O prêmio da loteria exige pagamento prévio do imposto.",
            "Receba o benefício emergencial cadastrando o cartão nesta página.",
            "Uma financeira libera crédito sem análise depois da tarifa inicial.",
            "Seu saldo esquecido será liberado após confirmar os códigos recebidos.",
            "A promoção oferece dinheiro grátis em troca de uma pequena transferência."
    );

    private static final List<String> MENSAGENS_SUSPEITAS = List.of(
            "Preciso que você confirme uma informação ainda hoje.",
            "Responda assim que puder porque o prazo termina amanhã.",
            "Há uma atualização importante aguardando sua análise.",
            "Entre em contato com o atendimento para resolver uma pendência.",
            "Recebemos uma solicitação incomum e precisamos falar com você.",
            "Não reconhecemos uma atividade recente no seu cadastro.",
            "Confirme se foi você quem realizou a última operação.",
            "Seu acesso precisa de uma revisão de segurança.",
            "Existe uma notificação urgente relacionada ao seu perfil.",
            "Verifique a origem desta mensagem antes de responder.",

            "Foi registrada uma compra que talvez você não reconheça.",
            "O banco solicita que procure uma agência para esclarecer a movimentação.",
            "Consulte diretamente a instituição sobre a tentativa de acesso.",
            "Seu cartão entrou em análise preventiva nesta manhã.",
            "Recebi um telefone desconhecido para tratar de uma cobrança bancária.",
            "Um contato pediu que eu abrisse o aplicativo durante a ligação.",
            "Uma transferência recente está aguardando confirmação.",
            "O atendimento deseja confirmar se seus dados continuam atualizados.",
            "Há divergência no cadastro bancário informado anteriormente.",
            "Procure seu gerente antes de realizar qualquer pagamento.",

            "Meu número mudou; salve este novo contato quando puder.",
            "Você consegue fazer a transferência combinada ontem?",
            "O pagamento do aluguel ainda não apareceu para mim.",
            "Confirme se o PIX de cinquenta reais chegou corretamente.",
            "Preciso devolver um valor que recebi por engano.",
            "A conta do fornecedor mudou e preciso conferir os documentos.",
            "Chegou um boleto atualizado sem confirmação do fornecedor.",
            "O caixa pediu que repetíssemos o pagamento recusado.",
            "O reembolso será processado depois da conferência bancária.",
            "Pedem que eu confirme os dados do destinatário de um pagamento não combinado.",

            "Uma oferta apareceu em um endereço parecido com o da loja conhecida.",
            "Recebi um endereço de rastreamento e ainda não verifiquei a procedência.",
            "Há um link na mensagem, mas prefiro abrir o aplicativo diretamente.",
            "O anúncio promete desconto alto somente até o fim do dia.",
            "Uma página desconhecida está pedindo atualização cadastral.",
            "O endereço parece diferente do site que costumo utilizar.",
            "Mandaram um formulário para confirmar a reserva do hotel.",
            "Recebi uma cobrança por e-mail com um botão para pagamento.",
            "O SMS contém um endereço curto que não reconheço.",
            "A loja enviou uma promoção com prazo muito reduzido.",

            "Uma consultoria apresentou investimento com rentabilidade acima da média.",
            "O vendedor afirmou que a oportunidade tem poucas vagas.",
            "Recebi convite para um grupo de discussões sobre criptomoedas.",
            "A proposta menciona ganhos rápidos, mas também fala em riscos.",
            "Um desconhecido ofereceu ajuda para montar minha carteira de investimentos.",
            "Uma corretora desconhecida insiste que eu aplique antes de ler os termos.",
            "Prometeram uma condição exclusiva para novos investidores.",
            "O anúncio usa depoimentos de pessoas que enriqueceram rapidamente.",
            "Fui convidado para uma palestra gratuita sobre renda extra.",
            "A plataforma informa que o saque pode levar alguns dias.",

            "Recebi uma proposta de trabalho sem ter enviado currículo.",
            "A entrevista será feita apenas por mensagens de texto.",
            "O recrutador pediu documentos antes de apresentar o contrato.",
            "A vaga oferece remuneração muito acima do mercado.",
            "A empresa quer que eu comece antes da assinatura formal.",
            "O anúncio de emprego não informa endereço nem CNPJ.",
            "Prometeram comissão por tarefas simples realizadas no celular.",
            "O contato do RH usa uma conta pessoal de mensagens.",
            "A empresa pediu dados bancários para um possível pagamento futuro.",
            "A oportunidade exige disponibilidade imediata e total sigilo.",

            "A transportadora informou que existe uma pendência na entrega.",
            "Meu pacote atrasou e recebi uma mensagem solicitando confirmação.",
            "Recebi um rastreamento que cobra taxa diretamente pela mensagem.",
            "Recebi uma segunda via de taxa relacionada à encomenda.",
            "O entregador pediu para confirmar o endereço por mensagem.",
            "A loja informou que pode haver cobrança adicional de importação.",
            "O pacote aparece como retido sem explicar o motivo.",
            "Uma mensagem diz que a entrega será cancelada em breve.",
            "A transportadora enviou um QR para acompanhar o pedido.",
            "O remetente da notificação não coincide com o nome da loja.",

            "O suporte pediu que eu descrevesse o erro apresentado no computador.",
            "Um técnico sugeriu instalar uma ferramenta que não conheço.",
            "Recebi um arquivo anexado que afirma ser um comprovante.",
            "O programa solicita permissões maiores do que parecem necessárias.",
            "A mensagem recomenda desativar temporariamente uma proteção.",
            "Um atendente quer acompanhar a configuração por chamada de vídeo.",
            "O aplicativo foi enviado diretamente, fora da loja oficial.",
            "O suporte afirma que precisa visualizar minha tela.",
            "Recebi instruções para executar um arquivo desconhecido.",
            "O contato insiste que eu permaneça ao telefone durante o procedimento.",

            "O comprador quer concluir toda a negociação fora da plataforma.",
            "Recebi um comprovante, mas o saldo ainda não apareceu.",
            "O cliente afirma ter pago um valor maior por acidente.",
            "A plataforma supostamente enviou um e-mail pedindo confirmação.",
            "O boleto recebido tem beneficiário diferente do esperado.",
            "O vendedor mudou a forma de pagamento no último momento.",
            "A pessoa quer usar um transportador que não conheço.",
            "O anúncio pede contato exclusivamente por aplicativo de mensagens.",
            "O intermediador solicita informações adicionais para liberar a compra.",
            "O pagamento aparece como pendente em uma captura de tela.",

            "Uma pessoa conhecida apenas pela internet pediu ajuda financeira.",
            "O contato ameaça divulgar informações caso eu não responda.",
            "Recebi uma cobrança relacionada a um processo que desconheço.",
            "Alguém se apresentou como autoridade sem fornecer identificação verificável.",
            "A mensagem menciona um familiar e exige resposta rápida.",
            "O remetente pede segredo sobre uma situação emergencial.",
            "Uma suposta instituição oferece acordo somente por mensagem.",
            "Recebi aviso de benefício sem ter feito solicitação.",
            "O contato afirma que existe dinheiro esquecido em meu nome.",
            "A proposta parece vantajosa demais e exige decisão imediata."
    );

    public static void main(String[] args) {
        validarQuantidade();
        boolean exibirDetalhes = args.length > 0
                && "--detalhes".equals(args[0]);

        DetectorGolpes detector = new DetectorGolpes();
        int golpesCorretos = 0;
        int golpesSuspeitos = 0;
        int golpesLegitimos = 0;
        int suspeitasCorretas = 0;
        int suspeitasComoGolpe = 0;
        int suspeitasComoLegitimas = 0;

        for (int indice = 0; indice < GOLPES.size(); indice++) {
            String mensagem = GOLPES.get(indice);
            ResultadoAnalise resultado = detector.analisar(mensagem);
            String nivel = resultado.nivelRisco();

            if (GOLPE.equals(nivel)) {
                golpesCorretos++;
            } else if (SUSPEITO.equals(nivel)) {
                golpesSuspeitos++;
            } else {
                golpesLegitimos++;
            }

            if (exibirDetalhes && !GOLPE.equals(nivel)) {
                imprimirDetalhe("G", indice, mensagem, resultado);
            }
        }

        for (int indice = 0;
             indice < MENSAGENS_SUSPEITAS.size();
             indice++) {
            String mensagem = MENSAGENS_SUSPEITAS.get(indice);
            ResultadoAnalise resultado = detector.analisar(mensagem);
            String nivel = resultado.nivelRisco();

            if (SUSPEITO.equals(nivel)) {
                suspeitasCorretas++;
            } else if (GOLPE.equals(nivel)) {
                suspeitasComoGolpe++;
            } else if (LEGITIMO.equals(nivel)) {
                suspeitasComoLegitimas++;
            }

            if (exibirDetalhes && !SUSPEITO.equals(nivel)) {
                imprimirDetalhe("S", indice, mensagem, resultado);
            }
        }

        System.out.println("GOLPES");
        System.out.println("Risco alto: " + golpesCorretos);
        System.out.println("Suspeito: " + golpesSuspeitos);
        System.out.println("Possivelmente legítimo: " + golpesLegitimos);
        System.out.println();
        System.out.println("MENSAGENS SUSPEITAS");
        System.out.println("Suspeito: " + suspeitasCorretas);
        System.out.println("Risco alto: " + suspeitasComoGolpe);
        System.out.println(
                "Possivelmente legítimo: " + suspeitasComoLegitimas
        );

        if (golpesCorretos != GOLPES.size()
                || suspeitasCorretas != MENSAGENS_SUSPEITAS.size()) {
            throw new AssertionError(
                    "As 200 mensagens devem receber a classificação esperada."
            );
        }
    }

    private static void validarQuantidade() {
        if (GOLPES.size() != 100
                || MENSAGENS_SUSPEITAS.size() != 100) {
            throw new AssertionError(
                    "A auditoria exige 100 golpes e 100 mensagens suspeitas."
            );
        }
    }

    private static void imprimirDetalhe(
            String grupo,
            int indice,
            String mensagem,
            ResultadoAnalise resultado
    ) {
        System.out.printf(
                "%s%03d | %d | %s | %s | %s%n",
                grupo,
                indice + 1,
                resultado.pontuacao(),
                resultado.nivelRisco(),
                mensagem,
                resultado.motivos()
        );
    }
}
