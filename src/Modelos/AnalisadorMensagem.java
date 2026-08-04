package Modelos;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Executa operações de baixo nível sobre o texto.
 * Responsabilidades:
 * - normalizar acentos e maiúsculas;
 * - procurar palavras e frases;
 * - identificar links;
 * - identificar valores monetários;
 * - identificar arquivos perigosos;
 * - analisar transferências.
 */
public final class AnalisadorMensagem {

    private static final Pattern MARCAS_DIACRITICAS =
            Pattern.compile("\\p{M}+");

    private static final Pattern VALOR_MONETARIO = Pattern.compile(
            "(?i)(?:"
                    + "r\\$\\s*\\d+(?:\\.\\d{3})*(?:,\\d{2})?"
                    + "|[\\$€£¥]\\s*\\d+(?:[.,]\\d{2})?"
                    + "|\\d{1,3}(?:\\.\\d{3})+,\\d{2}"
                    + "|\\d+(?:[.,]\\d{2})?\\s*"
                    + "(?:reais?|pilas?|contos?|usd|eur|gbp|btc|eth|jpy|cny|inr|brl)"
                    + ")"
    );

    private static final Pattern EMAIL = Pattern.compile(
            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
    );

    private static final Pattern ARQUIVO_PERIGOSO = Pattern.compile(
            "(?i)"
                    + "(?<![\\p{L}\\p{N}])"
                    + "[^\\s]+\\."
                    + "(?:"
                    + String.join("|", CatalogoGolpes.ARQUIVOS_PERIGOSOS)
                    + ")"
                    + "(?![\\p{L}\\p{N}])"
    );

    /**
     * Converte o texto para minúsculas e remove acentos.
     */
    public String normalizar(String texto) {
        String textoSeparado = Normalizer.normalize(
                texto.toLowerCase(Locale.ROOT),
                Normalizer.Form.NFD
        );

        return MARCAS_DIACRITICAS
                .matcher(textoSeparado)
                .replaceAll("");
    }

    /**
     * Verifica se pelo menos um termo da lista está presente.
     */
    public boolean contemAlgumTermo(
            String textoNormalizado,
            List<String> termos
    ) {
        for (String termo : termos) {
            if (contemTermo(textoNormalizado, termo)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Identifica links sem considerar um endereço de e-mail comum
     * como um link.
     */
    public boolean contemLink(String textoNormalizado) {
        if (contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.INDICIOS_LINK
        )) {
            return true;
        }

        String[] tokens = textoNormalizado.split("\\s+");

        for (String token : tokens) {
            String tokenLimpo = removerPontuacaoExterna(token);

            if (EMAIL.matcher(tokenLimpo).matches()) {
                continue;
            }

            for (String dominio : CatalogoGolpes.DOMINIOS_LINK) {
                if (tokenLimpo.endsWith(dominio)
                        || tokenLimpo.contains(dominio + "/")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Identifica extensões realmente terminadas em arquivos perigosos.
     * Assim, "arquivo.jar" é detectado, mas "arquivo.jardim" não.
     */
    public boolean contemArquivoPerigoso(String textoNormalizado) {
        return ARQUIVO_PERIGOSO
                .matcher(textoNormalizado)
                .find();
    }

    public boolean contemValorMonetario(String textoNormalizado) {
        return VALOR_MONETARIO
                .matcher(textoNormalizado)
                .find();
    }

    /**
     * Centraliza todos os indicadores relacionados a dinheiro.
     */
    public boolean contemIndicadorTransferencia(
            String textoNormalizado
    ) {
        return contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.TERMOS_TRANSFERENCIA
        )
                || contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.TERMOS_MOEDAS
        )
                || contemValorMonetario(textoNormalizado);
    }

    /**
     * Diferencia uma declaração comum de envio de uma solicitação.
     * Exemplo legítimo:
     * "Vou enviar um PIX de aniversário."
     */
    public boolean contemDeclaracaoEnvioLegitimo(
            String textoNormalizado
    ) {
        if (contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.DECLARACOES_TRANSFERENCIA_SUSPEITA
        )) {
            return false;
        }

        return contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.DECLARACOES_ENVIO_LEGITIMO
        )
                && contemIndicadorTransferencia(textoNormalizado);
    }

    /**
     * Detecta construções específicas consideradas suspeitas.
     */
    public boolean contemTransferenciaSuspeita(
            String textoNormalizado
    ) {
        return contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.DECLARACOES_TRANSFERENCIA_SUSPEITA
        )
                && contemIndicadorTransferencia(textoNormalizado);
    }

    /**
     * Um pedido de valor precisa conter:
     * - um valor monetário explícito; ou
     * - um verbo de solicitação junto de um indicador financeiro.
     * Isso reduz falsos positivos de palavras genéricas como "manda".
     */
    public boolean contemPedidoDeValor(
            String textoNormalizado
    ) {
        if (contemValorMonetario(textoNormalizado)) {
            return true;
        }

        boolean possuiVerboDePedido = contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.VERBOS_PEDIDO_VALOR
        );

        return possuiVerboDePedido
                && contemIndicadorTransferencia(textoNormalizado);
    }

    public boolean contemPedidoFinanceiro(
            String textoNormalizado
    ) {
        boolean possuiPedidoExplicito = contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.PEDIDOS_DINHEIRO
        );

        boolean possuiVerboDePedido = contemAlgumTermo(
                textoNormalizado,
                CatalogoGolpes.VERBOS_PEDIDO_VALOR
        );

        boolean possuiIndicadorFinanceiro =
                contemIndicadorTransferencia(textoNormalizado)
                        || contemValorMonetario(textoNormalizado);

        return possuiPedidoExplicito
                || (possuiVerboDePedido && possuiIndicadorFinanceiro);
    }

    /**
     * Pesquisa termos utilizando limites de palavra.
     * Termos com URL ou pontuação especial são pesquisados
     * diretamente.
     */
    private boolean contemTermo(String texto, String termo) {
        if (termo.contains(":")
                || termo.contains("/")
                || termo.contains(".")) {
            return texto.contains(termo);
        }

        Pattern padrao = Pattern.compile(
                "(?<![\\p{L}\\p{N}])"
                        + Pattern.quote(termo)
                        + "(?![\\p{L}\\p{N}])"
        );

        return padrao.matcher(texto).find();
    }

    private String removerPontuacaoExterna(String token) {
        return token.replaceAll(
                "^[('\"\\[]+|[),;!?'\"\\]]+$",
                ""
        );
    }
}