package modelos;

// Representa a pontuação e a explicação produzidas por uma regra encontrada.
public final class ResultadoRegra {
    private final int pontuacao;
    private final String motivo;

    public ResultadoRegra(int pontuacao, String motivo) {
        this.pontuacao = pontuacao;
        this.motivo = motivo;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getMotivo() {
        return motivo;
    }
}
