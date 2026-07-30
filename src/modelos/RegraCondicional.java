package modelos;

import java.util.Optional;
import java.util.function.Predicate;

// Regra reutilizável que transforma uma condição verdadeira em pontuação.
public final class RegraCondicional implements RegraAnalise {
    private final int pontuacao;
    private final String motivo;
    private final Predicate<ContextoAnalise> condicao;

    public RegraCondicional(int pontuacao, String motivo, Predicate<ContextoAnalise> condicao) {
        this.pontuacao = pontuacao;
        this.motivo = motivo;
        this.condicao = condicao;
    }

    @Override
    public Optional<ResultadoRegra> avaliar(ContextoAnalise contexto) {
        return condicao.test(contexto)
                ? Optional.of(new ResultadoRegra(pontuacao, motivo))
                : Optional.empty();
    }
}
