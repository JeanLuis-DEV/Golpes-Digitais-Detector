package Modelos;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

// Prepara a mensagem para que variações de maiúsculas e acentos não ocultem termos.
public final class NormalizadorMensagem {
    private static final Pattern MARCAS_DIACRITICAS = Pattern.compile("\\p{M}+");

    public String normalizar(String texto) {
        String textoComAcentosSeparados = Normalizer.normalize(
                texto.toLowerCase(Locale.ROOT),
                Normalizer.Form.NFD
        );

        return MARCAS_DIACRITICAS.matcher(textoComAcentosSeparados).replaceAll("");
    }
}
