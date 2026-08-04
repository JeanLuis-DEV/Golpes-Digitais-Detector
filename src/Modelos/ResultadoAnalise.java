package Modelos;

import java.util.List;

/**
 * Representa o resultado final da análise.
 * <p>
 * O record reduz a necessidade de construtor, getters e outros
 * códigos repetitivos.
 */
public record ResultadoAnalise(
        String nivelRisco,
        int pontuacao,
        List<String> motivos
) {

    public ResultadoAnalise {
        motivos = List.copyOf(motivos);
    }
}