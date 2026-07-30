package modelos;

import java.util.Optional;

// Contrato de uma regra independente usada pelo detector.
public interface RegraAnalise {
    Optional<ResultadoRegra> avaliar(ContextoAnalise contexto);
}
