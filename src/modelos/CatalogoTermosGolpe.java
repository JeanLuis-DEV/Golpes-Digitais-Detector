package modelos;

import java.util.List;

// Centraliza os termos usados pelo detector para facilitar consultas e manutenção.
public final class CatalogoTermosGolpe {
    public static final List<String> TERMOS_PRESSAO_OU_ACAO = List.of(
            "urgente", "agora", "imediatamente", "ultima chance", "na hora",
            "corre", "rapidinho", "o quanto antes", "sem demora", "ainda hoje",
            "hoje mesmo", "pra hoje", "para hoje", "ultimos minutos", "ultimas vagas",
            "tempo limitado", "expira hoje", "vai expirar", "antes que expire",
            "nao perca tempo", "responda rapido", "resolve agora", "so ate hoje",
            "pra ja", "para ja",
            "vote em mim", "vote neste", "clique para votar", "cadastre-se",
            "retorne nesse contato", "retorne neste contato", "retorne o contato",
            "entre em contato", "responda essa mensagem", "responda esta mensagem",
            "chame no numero", "ligue para", "ligue", "me chama", "para resgatar",
            "pra resgatar", "resgate agora", "da um alo", "chama no zap",
            "me da um toque", "pra pegar", "para pegar",
            "confirme agora", "confirme seus dados", "confirme sua conta",
            "atualize seus dados", "atualize seu cadastro", "regularize sua conta",
            "desbloqueie sua conta", "libere seu acesso", "ative sua conta",
            "liberar", "para liberar", "pra liberar",
            "valide sua identidade", "valide seu cadastro", "fale comigo",
            "fala comigo", "chama aqui", "chama privado", "chama no privado",
            "me chama no privado", "manda mensagem", "mande mensagem",
            "responde aqui", "retorna aqui", "me liga", "liga pra mim",
            "salva esse numero", "adicione este numero"
    );

    public static final List<String> TERMOS_PREMIO_OU_PROMESSA = List.of(
            "premio", "ganhou", "ganhar", "ganhe", "sorteado", "dinheiro facil",
            "acaba de receber", "voce recebeu", "valor liberado", "saldo liberado",
            "promocao", "promo", "bolada no teu nome", "bolada no seu nome",
            "grana parada te esperando", "grana te esperando",
            "foi premiado", "foi premiada", "premiacao", "recompensa",
            "bonus exclusivo", "bonus liberado", "credito disponivel", "credito liberado",
            "dinheiro esperando", "valor a receber", "resgate seu valor",
            "resgate seu premio", "cupom premiado", "raspadinha premiada",
            "presente surpresa", "beneficio liberado", "beneficio aprovado",
            "cashback liberado", "sorteio exclusivo", "contemplacao"
    );

    public static final List<String> TERMOS_DADOS_OU_IDENTIDADE = List.of(
            "senha", "cpf", "dados bancarios", "codigo de verificacao",
            "foto sua", "sua foto", "seu email", "seu e-mail",
            "endereco de email", "endereco de e-mail",
            "passa os 6 digitos", "passe os 6 digitos",
            "passar os 6 digitos", "digitos que chegaram",
            "numero do cartao", "dados do cartao", "validade do cartao",
            "codigo do cartao", "cvv", "codigo cvc", "cvc do cartao",
            "informe o cvc", "manda o cvc", "passe o cvc",
            "senha do banco", "senha bancaria",
            "senha do aplicativo", "senha do app", "token de acesso", "token bancario",
            "codigo sms", "codigo por sms", "codigo recebido", "codigo que chegou",
            "confirme o codigo", "informe o codigo", "manda o codigo", "passe o codigo",
            "chave de seguranca", "data de nascimento", "nome completo",
            "numero da conta", "numero da agencia", "foto do documento",
            "foto do rg", "foto da cnh", "selfie com documento", "biometria facial",
            "esse e meu numero novo", "este e meu numero novo",
            "meu novo numero", "troquei de numero", "mudei de numero",
            "perdi meu zap antigo", "perdi meu whatsapp antigo",
            "meu cel foi pro saco", "meu celular foi pro saco",
            "aparelho de um parceiro", "aparelho de uma parceira",
            "troca meu contato", "troque meu contato",
            "numero antigo nao funciona", "nao uso mais o outro numero",
            "apaga meu numero antigo", "salva meu contato novo",
            "estou com outro chip", "to com outro chip", "celular quebrou",
            "celular estragou", "perdi meu celular", "roubaram meu celular",
            "estou usando outro aparelho", "to usando outro aparelho",
            "contato temporario", "whatsapp novo", "zap novo",
            "central de seguranca", "central antifraude", "setor antifraude",
            "equipe de seguranca", "suporte tecnico", "suporte do banco",
            "gerente da conta", "atendimento bancario", "representante do banco",
            "somos do banco", "falo em nome do banco", "equipe do pix",
            "central do cartao", "operadora do cartao", "receita federal",
            "correios informa", "mercado pago informa", "nubank informa",
            "caixa informa", "banco central informa",
            "conta bloqueada", "conta sera bloqueada", "conta suspensa",
            "acesso suspenso", "cartao bloqueado", "cartao sera cancelado",
            "cadastro irregular", "cadastro pendente", "cpf irregular",
            "cpf suspenso", "beneficio suspenso", "beneficio sera cancelado",
            "multa pendente", "divida pendente", "pagamento recusado",
            "compra suspeita", "transacao suspeita", "acesso nao reconhecido",
            "dispositivo desconhecido", "tentativa de acesso"
    );

    public static final List<String> TERMOS_FINANCEIROS = List.of(
            "dinheiro emprestado", "preciso de dinheiro", "emprestar", "empresta",
            "receba", "fature", "lucre", "lucro", "renda extra",
            "dinheiro rapido", "valor disponivel", "cashback", "retorno financeiro",
            "saque seu dinheiro", "resgate seu dinheiro",
            "manda dinheiro", "mande dinheiro", "mandar um pix", "manda um pix",
            "fazer um pix", "faz um pix", "depois eu te pago",
            "me ajuda com dinheiro", "me ajuda com uma grana", "quebra essa pra mim",
            "faz essa pra mim", "to precisando de uma grana", "preciso de uma ajuda",
            "preciso pagar uma conta", "estou sem dinheiro", "to sem dinheiro",
            "adianta um valor", "adianta uma grana", "empresta uma grana",
            "manda uma ajuda", "faz um deposito", "paga pra mim",
            "manda", "mande", "envie", "pague", "apoiar", "apoie",
            "empreste", "quebra", "adianta", "adiantar", "acerta",
            "taxinha de", "forca de", "faz a boa de", "entrar com",
            "contribui", "contribua", "colabora", "colabore", "desembolsa",
            "completa", "cobre", "banca", "antecipa", "antecipe",
            "faz chegar", "manda ai", "manda pra ca", "libera", "liberar", "paga",
            "pix do meu amigo", "pix de um amigo", "conta do meu amigo",
            "conta de um amigo", "conta de outra pessoa",
            "enviar direto pro", "enviar direto para", "mandar direto pro",
            "mandar direto para", "manda pro", "manda para", "mandar para",
            "direto para a loja", "conta do meu primo", "conta de um primo",
            "conta da minha prima", "conta de uma prima", "pix do meu parente",
            "pix de um parente", "conta do fornecedor", "conta do entregador",
            "conta do motoboy", "pix do vendedor", "pix da loja",
            "chave de outra pessoa", "titular diferente",
            "nome de outra pessoa", "conta de terceiro",
            "taxa de liberacao", "taxa para liberar", "pague a taxa",
            "pagamento antecipado", "deposito antecipado", "taxa administrativa",
            "taxa de cadastro", "taxa de envio", "taxa de entrega",
            "taxa alfandegaria", "taxa dos correios", "taxa do premio",
            "custo de desbloqueio", "valor para liberar", "pagar para receber",
            "liberacao mediante pagamento", "frete pendente", "encomenda retida",
            "pacote retido", "pedido retido",
            "lucro garantido", "retorno garantido", "renda garantida",
            "ganho garantido", "investimento sem risco", "renda extra imediata",
            "dinheiro automatico", "multiplique seu dinheiro", "dobre seu dinheiro",
            "triplique seu dinheiro", "investimento secreto", "metodo infalivel",
            "robo de investimento", "sinais premium",
            "grupo vip de investimento", "rentabilidade garantida",
            "ganhe por dia", "saque imediato", "oportunidade imperdivel"
    );

    public static final List<String> TERMOS_MOVIMENTACAO_FINANCEIRA = List.of(
            "pix", "transferencia", "deposito", "ted", "doc bancario",
            "boleto", "pagar boleto", "pagamento", "transferir", "transfere",
            "transfira", "depositar", "deposite", "mandar valor", "enviar valor",
            "chave pix", "copia e cola", "qr code pix", "pix copia e cola",
            "carteira digital", "recarga", "gift card", "cartao presente"
    );

    public static final List<String> TERMOS_AMEACA_DIGITAL = List.of(
            ".exe", ".bat", ".cmd", ".scr", ".msi", ".jar", ".apk",
            ".vbs", ".ps1", ".hta", ".pif", ".cpl", ".reg", ".wsf",
            ".gadget", ".application",
            "instale anydesk", "baixe anydesk", "abra o anydesk",
            "instale teamviewer", "baixe teamviewer", "abra o teamviewer",
            "instale rustdesk", "baixe rustdesk", "acesso remoto",
            "controle remoto", "compartilhe sua tela", "compartilhar a tela",
            "mostre sua tela", "espelhe sua tela", "permita o acesso",
            "codigo do anydesk", "id do teamviewer", "senha do acesso remoto",
            "aplicativo de suporte", "app de suporte"
    );

    public static final List<String> INDICIOS_DE_LINK = List.of(
            "http://", "https://", "www.", "bit.ly", "link:", "link.",
            "acesse:", "acesse o link", "abra o link", "link abaixo", "toque aqui",
            "clique aqui", "confira aqui", "bit.do", "tinyurl.com", "t.co",
            "cutt.ly", "encurtador.com.br"
    );

    public static final List<String> DOMINIOS_DE_LINK = List.of(
            ".com", ".org", ".net", ".br", ".io", ".co", ".uk", ".eu",
            ".us", ".ca", ".de", ".fr", ".es", ".pt", ".it", ".nl",
            ".ru", ".cn", ".jp", ".in", ".au", ".mx", ".ar",
            ".xyz", ".top", ".site", ".online", ".info", ".biz", ".club",
            ".shop", ".live", ".click", ".link", ".me", ".tv", ".cc", ".tk"
    );

    private CatalogoTermosGolpe() {
    }
}
