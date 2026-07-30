package modelos;

import java.util.List;

// Representa o resultado final produzido pelo Modelos.DetectorGolpes.
public final class ResultadoAnalise {
    // Guarda a classificação, a pontuação e os motivos encontrados.
    private final String nivelRisco;
    private final int pontuacao;
    private final List<String> motivos;

    // Recebe os valores calculados pelo detector e monta o resultado.
    public ResultadoAnalise(String nivelRisco, int pontuacao, List<String> motivos) {
        this.nivelRisco = nivelRisco;
        this.pontuacao = pontuacao;
        this.motivos = List.copyOf(motivos);
    }

    // Os métodos get permitem consultar os dados sem alterá-los.
    public String getNivelRisco() {
        return nivelRisco;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public List<String> getMotivos() {
        return motivos;
    }
}
