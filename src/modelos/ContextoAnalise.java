package modelos;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

// Reúne os dados compartilhados por todas as regras durante uma análise.
public final class ContextoAnalise {
    private final String mensagem;
    private final Map<Object, Boolean> resultadosCalculados = new IdentityHashMap<>();

    public ContextoAnalise(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    boolean obterResultado(Object regra, BooleanSupplier calculo) {
        return resultadosCalculados.computeIfAbsent(
                regra,
                chave -> calculo.getAsBoolean()
        );
    }
}
