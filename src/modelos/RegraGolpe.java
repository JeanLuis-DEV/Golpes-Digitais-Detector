package Modelos;

import java.util.List;

/**
 * Define uma regra simples de detecção.
 * <p>
 * Cada regra guarda:
 * - os termos procurados;
 * - a quantidade de pontos;
 * - o motivo apresentado ao usuário.
 */
public record RegraGolpe(
        List<String> termos,
        int pontos,
        String motivo
) {

    public RegraGolpe {
        termos = List.copyOf(termos);

        if (pontos < 0) {
            throw new IllegalArgumentException(
                    "A pontuação não pode ser negativa."
            );
        }

        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException(
                    "O motivo não pode estar vazio."
            );
        }
    }
}