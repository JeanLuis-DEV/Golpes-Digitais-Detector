package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Execucaoif implements Contem {
    private final List<String> motivos = new ArrayList<>();

    public List<String> getMotivos() {
        return List.copyOf(this.motivos);
    }

    @Override
    public int verificacao(Boolean condicaoTermo, int pontosDeteccao, String motivo) {
        if (condicaoTermo) {
            motivos.add(motivo);
            return pontosDeteccao;
        }
        return 0;
    }
}
