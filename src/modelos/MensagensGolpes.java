package modelos;

public class MensagensGolpes {
    // Cada lista reúne palavras ou trechos ligados a um tipo de golpe.
    protected static final String[] TERMOS_URGENCIA = {
            "urgente", "agora", "imediatamente", "ultima chance", "na hora"
    };

    protected static final String[] TERMOS_PREMIO = {
            "premio", "ganhou", "ganhar", "ganhe", "sorteado", "dinheiro facil"
    };

    protected static final String[] TERMOS_DADOS_PESSOAIS = {
            "senha", "cpf", "dados bancarios", "codigo de verificacao"
    };

    protected static final String[] INDICIOS_DE_LINK = {
            "http://", "https://", "www.", "bit.ly", "link:", "link."
    };

    protected static final String[] DOMINIOS_DE_LINK = {
            ".com", ".org", ".net", ".br", ".io", ".co", ".uk", ".eu",
            ".us", ".ca", ".de", ".fr", ".es", ".pt", ".it", ".nl",
            ".ru", ".cn", ".jp", ".in", ".au", ".mx", ".ar",
            ".xyz", ".top", ".site", ".online", ".info", ".biz", ".club",
            ".shop", ".live", ".click", ".link", ".me", ".tv", ".cc", ".tk"
    };

    protected static final String[] PEDIDOS_DE_ACAO = {
            "vote em mim", "vote neste", "clique para votar", "cadastre-se"
    };

    protected static final String[] ARQUIVOS_PERIGOSOS = {
            ".exe", ".bat", ".cmd", ".scr", ".msi", ".jar", ".apk"
    };

    protected static final String[] TERMOS_TRANSFERENCIA = {
            "pix", "transferencia", "deposito"
    };

    protected static final String[] PEDIDOS_DE_DINHEIRO = {
            "dinheiro emprestado", "preciso de dinheiro", "emprestar", "empresta",
            "manda dinheiro", "mande dinheiro", "mandar um pix", "manda um pix",
            "fazer um pix", "faz um pix", "depois eu te pago"
    };

    protected static final String[] PAGAMENTO_PARA_TERCEIROS = {
            "pix do meu amigo", "pix de um amigo", "conta do meu amigo", "conta de um amigo",
            "conta de outra pessoa",
            "enviar direto pro", "enviar direto para", "mandar direto pro", "mandar direto para",
            "manda pro", "manda para", "mandar para", "direto para a loja"
    };
}
