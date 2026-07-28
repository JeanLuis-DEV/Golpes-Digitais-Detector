package modelos;

import java.text.Normalizer;
import java.util.ArrayList;

// Contém todas as regras usadas para analisar uma mensagem.
public class DetectorGolpes extends MensagensGolpes{
    // Limites usados para transformar a pontuação em nível de risco.
    private static final int LIMITE_RISCO_MEDIO = 4;
    private static final int LIMITE_RISCO_ALTO = 7;
    ContemTexto contem = new ContemTexto();
    // Analisa uma mensagem e devolve sua pontuação, classificação e motivos.
    public ResultadoAnalise analisar(String mensagem) {
        // Não é possível analisar um texto nulo ou vazio.
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }

        // Letras minúsculas e sem acentos evitam que erros de acentuação escondam os termos.
        String mensagemNormalizada = normalizarTexto(mensagem);
        int pontuacao = 0;
        ArrayList<String> motivos = new ArrayList<>();

        // A urgência é usada para fazer a pessoa agir sem pensar.
        if (contem.contemAlgumTermo(mensagemNormalizada, TERMOS_URGENCIA)) {
            pontuacao += 2;
            motivos.add("A mensagem tenta criar urgência.");
        }

        // Promessas de prêmio ou dinheiro fácil também são sinais comuns.
        if (contem.contemAlgumTermo(mensagemNormalizada, TERMOS_PREMIO)) {
            pontuacao += 2;
            motivos.add("A mensagem oferece prêmio ou dinheiro fácil.");
        }

        // Pedidos de dados pessoais recebem uma pontuação maior.
        if (contem.contemAlgumTermo(mensagemNormalizada, TERMOS_DADOS_PESSOAIS)) {
            pontuacao += 3;
            motivos.add("A mensagem solicita dados pessoais ou bancários.");
        }

        // Um endereço de site pode aparecer com ou sem http.
        if (contem.contemLink(mensagemNormalizada)) {
            pontuacao += 3;
            motivos.add("A mensagem contém um link.");
        }

        // A mensagem pode tentar convencer a vítima a clicar, votar ou se cadastrar.
        if (contem.contemAlgumTermo(mensagemNormalizada, PEDIDOS_DE_ACAO)) {
            pontuacao += 2;
            motivos.add("A mensagem pede uma ação em troca de uma vantagem.");
        }

        // Arquivos executáveis já são suficientes para indicar risco alto.
        if (contem.contemAlgumTermo(mensagemNormalizada, ARQUIVOS_PERIGOSOS)) {
            pontuacao += 7;
            motivos.add("A mensagem contém um arquivo que pode executar programas maliciosos.");
        }

        // Transferências de dinheiro exigem atenção.
        if (contem.contemAlgumTermo(mensagemNormalizada, TERMOS_TRANSFERENCIA)) {
            pontuacao += 3;
            motivos.add("A mensagem solicita uma transferência de dinheiro.");
        }

        // O pedido de dinheiro aumenta o risco quando aparece com outros sinais.
        if (contem.contemAlgumTermo(mensagemNormalizada, PEDIDOS_DE_DINHEIRO)) {
            pontuacao += 2;
            motivos.add("A mensagem contém um pedido de dinheiro.");
        }

        // Enviar o pagamento para outra pessoa é um comportamento suspeito.
        if (contem.contemAlgumTermo(mensagemNormalizada, PAGAMENTO_PARA_TERCEIROS)) {
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

    // Converte o texto para minúsculas, separa os acentos e depois os remove.
    private String normalizarTexto(String texto) {
        String textoComAcentosSeparados = Normalizer.normalize(
                texto.toLowerCase(),
                Normalizer.Form.NFD
        );

        return textoComAcentosSeparados.replaceAll("\\p{M}", "");
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
