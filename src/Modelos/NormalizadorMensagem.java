package Modelos;

import java.text.Normalizer;
import java.util.Locale;

// Prepara a mensagem para que variações de maiúsculas e acentos não ocultem termos.
public class NormalizadorMensagem {
    public String normalizar(String texto) {
        String textoComAcentosSeparados = Normalizer.normalize(
                texto.toLowerCase(Locale.ROOT),
                Normalizer.Form.NFD
        );

        return textoComAcentosSeparados.replaceAll("\\p{M}", "");
    }
}