package Modelos;

import java.util.ArrayList;

// Representa o resultado final produzido pelo Modelos.DetectorGolpes.
public class ResultadoAnalise {
    // Guarda a classificação, a pontuação e os motivos encontrados.
    private final String nivelRisco;
    private final int pontuacao;
    private final ArrayList<String> motivos;

    // Recebe os valores calculados pelo detector e monta o resultado.
    public ResultadoAnalise(String nivelRisco, int pontuacao, ArrayList<String> motivos) {
        this.nivelRisco = nivelRisco;
        this.pontuacao = pontuacao;
        this.motivos = new ArrayList<>(motivos);
    }

    // Os métodos get permitem consultar os dados sem alterá-los.
    public String getNivelRisco() {
        return nivelRisco;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public ArrayList<String> getMotivos() {
        // Retorna uma cópia para proteger a lista original do resultado.
        return new ArrayList<>(motivos);
    }
}
