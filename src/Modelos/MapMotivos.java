package Modelos;

import java.util.HashMap;

public class MapMotivos {
    private HashMap<String, String> listaMotivos = new HashMap<>();

    public MapMotivos() {

    }

    public String getListaMotivos(String chave) {
        return listaMotivos.get(chave);
    }
}
