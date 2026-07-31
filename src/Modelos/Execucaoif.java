package Modelos;

import java.util.ArrayList;

public class Execucaoif implements Contem{
    @Override
    public int verificacao(Boolean condicaoTermo, int pontosDeteccao, String motivo, ArrayList<String> motivos) {
        if (condicaoTermo) {
            motivos.add(motivo);
            return pontosDeteccao;
        }
        return 0;
    }
}
