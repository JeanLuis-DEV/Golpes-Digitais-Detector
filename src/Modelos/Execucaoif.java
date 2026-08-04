package Modelos;

import java.util.ArrayList;

public class Execucaoif implements Contem {
    private final ArrayList<String> motivos = new ArrayList<>();

    public ArrayList<String> getMotivos() {
        return motivos;
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