package modelos;

import java.text.Normalizer;

public class NormalizadorTexto {
    // Converte o texto para minúsculas, separa os acentos e depois os remove.
    public String normalizarTexto(String texto) {
        String textoComAcentosSeparados = Normalizer.normalize(
                texto.toLowerCase(),
                Normalizer.Form.NFD
        );

        return textoComAcentosSeparados.replaceAll("\\p{M}", "");
    }
}
