package Modelos;

import java.util.ArrayList;
import java.util.HashMap;

// Coordena a análise da mensagem e aplica as regras de pontuação.
public class DetectorGolpes {
    private final NormalizadorMensagem normalizador = new NormalizadorMensagem();
    private final ClassificadorRisco classificadorRisco = new ClassificadorRisco();

    // Analisa uma mensagem e devolve sua pontuação, classificação e motivos.
    public ResultadoAnalise analisar(String mensagem) {
        validarMensagem(mensagem);

        String mensagemNormalizada = normalizador.normalizar(mensagem);

        Execucaoif execucao = new Execucaoif();

        Verificacoes verificar = new Verificacoes();

        int pontuacao = verificar.contemTermos(mensagemNormalizada);

        String nivelRisco = classificadorRisco.classificar(pontuacao);

        return new ResultadoAnalise(nivelRisco, pontuacao, execucao.getMotivos());
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }
    }

}
