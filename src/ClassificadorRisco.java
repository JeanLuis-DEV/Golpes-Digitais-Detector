// Converte a pontuação calculada em um nível de risco.
public class ClassificadorRisco {
    private static final int LIMITE_RISCO_MEDIO = 4;
    private static final int LIMITE_RISCO_ALTO = 7;

    public String classificar(int pontuacao) {
        if (pontuacao >= LIMITE_RISCO_ALTO) {
            return "ALTO";
        }

        if (pontuacao >= LIMITE_RISCO_MEDIO) {
            return "MÉDIO";
        }

        return "BAIXO";
    }
}
