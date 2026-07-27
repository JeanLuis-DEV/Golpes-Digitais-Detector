import java.util.ArrayList;

// Contém todas as regras usadas para analisar uma mensagem.
public class DetectorGolpes {
    // Limites usados para transformar a pontuação em nível de risco.
    private static final int LIMITE_RISCO_MEDIO = 4;
    private static final int LIMITE_RISCO_ALTO = 7;

    // Cada lista reúne palavras ou trechos ligados a um tipo de golpe.
    private static final String[] TERMOS_URGENCIA = {
            "urgente", "agora", "imediatamente", "última chance", "na hora"
    };

    private static final String[] TERMOS_PREMIO = {
            "prêmio", "ganhou", "ganhar", "ganhe", "sorteado", "dinheiro fácil"
    };

    private static final String[] TERMOS_DADOS_PESSOAIS = {
            "senha", "cpf", "dados bancários", "código de verificação"
    };

    private static final String[] INDICIOS_DE_LINK = {
            "http://", "https://", "www.", "bit.ly", "link:", "link."
    };

    private static final String[] DOMINIOS_DE_LINK = {
            ".com", ".org", ".net", ".br", ".io", ".co", ".uk", ".eu",
            ".us", ".ca", ".de", ".fr", ".es", ".pt", ".it", ".nl",
            ".ru", ".cn", ".jp", ".in", ".au", ".mx", ".ar",
            ".xyz", ".top", ".site", ".online", ".info", ".biz", ".club",
            ".shop", ".live", ".click", ".link", ".me", ".tv", ".cc", ".tk"
    };

    private static final String[] PEDIDOS_DE_ACAO = {
            "vote em mim", "vote neste", "clique para votar", "cadastre-se"
    };

    private static final String[] ARQUIVOS_PERIGOSOS = {
            ".exe", ".bat", ".cmd", ".scr", ".msi", ".jar", ".apk"
    };

    private static final String[] TERMOS_TRANSFERENCIA = {
            "pix", "transferência", "depósito"
    };

    private static final String[] PEDIDOS_DE_DINHEIRO = {
            "dinheiro emprestado", "preciso de dinheiro", "emprestar", "empresta",
            "manda dinheiro", "mande dinheiro", "mandar um pix", "manda um pix",
            "fazer um pix", "faz um pix", "depois eu te pago"
    };

    private static final String[] PAGAMENTO_PARA_TERCEIROS = {
            "pix do meu amigo", "pix de um amigo", "conta do meu amigo", "conta de outra pessoa",
            "enviar direto pro", "enviar direto para", "mandar direto pro", "mandar direto para",
            "manda pro", "mandar para", "direto para a loja"
    };

    // Analisa uma mensagem e devolve sua pontuação, classificação e motivos.
    public ResultadoAnalise analisar(String mensagem) {
        // Não é possível analisar um texto nulo ou vazio.
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }

        // As letras minúsculas facilitam a comparação com as listas de termos.
        String mensagemNormalizada = mensagem.toLowerCase();
        int pontuacao = 0;
        ArrayList<String> motivos = new ArrayList<>();

        // Regra 1: mensagens que pressionam a pessoa a agir rapidamente.
        if (contemAlgumTermo(mensagemNormalizada, TERMOS_URGENCIA)) {
            pontuacao += 2;
            motivos.add("A mensagem tenta criar urgência.");
        }

        // Regra 2: promessas de prêmio ou dinheiro fácil.
        if (contemAlgumTermo(mensagemNormalizada, TERMOS_PREMIO)) {
            pontuacao += 2;
            motivos.add("A mensagem oferece prêmio ou dinheiro fácil.");
        }

        // Regra 3: pedidos de informações pessoais ou bancárias.
        if (contemAlgumTermo(mensagemNormalizada, TERMOS_DADOS_PESSOAIS)) {
            pontuacao += 3;
            motivos.add("A mensagem solicita dados pessoais ou bancários.");
        }

        // Regra 4: links escritos de formas diferentes ou com vários domínios.
        if (contemAlgumTermo(mensagemNormalizada, INDICIOS_DE_LINK)
                || contemAlgumTermo(mensagemNormalizada, DOMINIOS_DE_LINK)) {
            pontuacao += 3;
            motivos.add("A mensagem contém um link.");
        }

        // Regra 5: pedidos de cadastro, clique ou voto em troca de vantagem.
        if (contemAlgumTermo(mensagemNormalizada, PEDIDOS_DE_ACAO)) {
            pontuacao += 2;
            motivos.add("A mensagem pede uma ação em troca de uma vantagem.");
        }

        // Regra 6: arquivos executáveis recebem pontuação alta por serem perigosos.
        if (contemAlgumTermo(mensagemNormalizada, ARQUIVOS_PERIGOSOS)) {
            pontuacao += 7;
            motivos.add("A mensagem contém um arquivo que pode executar programas maliciosos.");
        }

        // Regra 7: pedidos de PIX, transferência ou depósito.
        if (contemAlgumTermo(mensagemNormalizada, TERMOS_TRANSFERENCIA)) {
            pontuacao += 3;
            motivos.add("A mensagem solicita uma transferência de dinheiro.");
        }

        // Regra 8: pedidos para emprestar ou enviar dinheiro.
        if (contemAlgumTermo(mensagemNormalizada, PEDIDOS_DE_DINHEIRO)) {
            pontuacao += 2;
            motivos.add("A mensagem contém um pedido de dinheiro.");
        }

        // Regra 9: pagamentos enviados para uma pessoa diferente do remetente.
        if (contemAlgumTermo(mensagemNormalizada, PAGAMENTO_PARA_TERCEIROS)) {
            pontuacao += 2;
            motivos.add("O pagamento solicitado seria enviado para outra pessoa.");
        }

        // Converte a pontuação em nível de risco e reúne todos os dados.
        String nivelRisco = classificarRisco(pontuacao);
        return new ResultadoAnalise(nivelRisco, pontuacao, motivos);
    }

    // Procura na mensagem pelo menos um dos termos recebidos.
    private boolean contemAlgumTermo(String mensagem, String[] termos) {
        for (String termo : termos) {
            if (mensagem.contains(termo)) {
                return true;
            }
        }

        return false;
    }

    // Classifica o risco de acordo com os limites definidos no início da classe.
    private String classificarRisco(int pontuacao) {
        if (pontuacao >= LIMITE_RISCO_ALTO) {
            return "ALTO";
        }

        if (pontuacao >= LIMITE_RISCO_MEDIO) {
            return "MÉDIO";
        }

        return "BAIXO";
    }
}
