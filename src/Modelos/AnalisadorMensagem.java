package Modelos;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
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

    private static final Pattern DOMINIO = Pattern.compile(
            "(?:https?://|www\\.)?"
                    + "([a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
                    + "(?:\\.[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)+)"
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
     * Remove endereços da cópia usada nas regras de contexto. Isso impede
     * que palavras existentes apenas no domínio ou no caminho da URL sejam
     * interpretadas como pedidos de senha, conta ou pagamento.
     */
    public String removerLinks(String textoNormalizado) {
        StringBuilder textoSemLinks = new StringBuilder();

        for (String token : textoNormalizado.split("\\s+")) {
            String tokenLimpo = removerPontuacaoExterna(token);

            if (!EMAIL.matcher(tokenLimpo).matches()
                    && tokenContemDominioDeLink(tokenLimpo)) {
                textoSemLinks.append(' ');
                continue;
            }

            textoSemLinks.append(token).append(' ');
        }

        return textoSemLinks.toString().trim();
    }

    /**
     * Procura termos de solicitação, mas ignora orientações explícitas
     * como "não passe sua senha" ou "nunca informe o código".
     */
    public boolean contemAlgumTermoSemNegacao(
            String textoNormalizado,
            List<String> termos
    ) {
        for (String termo : termos) {
            Pattern padrao = criarPadraoTermo(termo);
            Matcher correspondencia = padrao.matcher(textoNormalizado);

            while (correspondencia.find()) {
                String textoAnterior = textoNormalizado.substring(
                        Math.max(0, correspondencia.start() - 15),
                        correspondencia.start()
                );

                if (!textoAnterior.matches(
                        ".*(?:nao|nunca|jamais)\\s+$"
                )) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Reconhece flexões verbais por radical, como "informar", "informou"
     * e "informe", sem cadastrar uma frase completa para cada variação.
     */
    public boolean contemRadicalSemNegacao(
            String textoNormalizado,
            List<String> radicais
    ) {
        String[] palavras = textoNormalizado.split(
                "[^\\p{L}\\p{N}]+"
        );

        for (int indice = 0; indice < palavras.length; indice++) {
            if (palavras[indice].isEmpty()
                    || estaSobNegacao(palavras, indice)) {
                continue;
            }

            for (String radical : radicais) {
                if (palavras[indice].startsWith(radical)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Identifica endereços que não pertencem aos domínios oficiais
     * cadastrados. Um domínio oficial pode ter subdomínios.
     */
    public boolean contemLinkDesconhecido(String textoNormalizado) {
        for (String dominio : extrairDominios(textoNormalizado)) {
            if (!dominioEhOficial(dominio)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Detecta domínios não oficiais que tentam parecer uma marca conhecida.
     */
    public boolean contemImitacaoDeDominioOficial(
            String textoNormalizado
    ) {
        for (String dominio : extrairDominios(textoNormalizado)) {
            if (dominioEhOficial(dominio)) {
                continue;
            }

            for (String parte : dominio.split("\\.")) {
                String parteSemHifen = parte.replace("-", "");

                if (pareceMarcaProtegida(parteSemHifen)) {
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
        return criarPadraoTermo(termo).matcher(texto).find();
    }

    private Pattern criarPadraoTermo(String termo) {
        if (termo.contains(":")
                || termo.contains("/")
                || termo.contains(".")) {
            return Pattern.compile(Pattern.quote(termo));
        }

        return Pattern.compile(
                "(?<![\\p{L}\\p{N}])"
                        + Pattern.quote(termo)
                        + "(?![\\p{L}\\p{N}])"
        );
    }

    private boolean estaSobNegacao(String[] palavras, int indice) {
        int inicio = Math.max(0, indice - 2);

        for (int anterior = inicio; anterior < indice; anterior++) {
            if ("nao".equals(palavras[anterior])
                    || "nunca".equals(palavras[anterior])
                    || "jamais".equals(palavras[anterior])) {
                return true;
            }
        }

        return false;
    }

    private List<String> extrairDominios(String textoNormalizado) {
        List<String> dominios = new ArrayList<>();

        for (String token : textoNormalizado.split("\\s+")) {
            String tokenLimpo = removerPontuacaoExterna(token);

            if (EMAIL.matcher(tokenLimpo).matches()) {
                continue;
            }

            if (!tokenContemDominioDeLink(tokenLimpo)) {
                continue;
            }

            Matcher correspondencia = DOMINIO.matcher(tokenLimpo);

            while (correspondencia.find()) {
                dominios.add(correspondencia.group(1));
            }
        }

        return dominios;
    }

    private boolean tokenContemDominioDeLink(String token) {
        if (token.startsWith("http://")
                || token.startsWith("https://")
                || token.startsWith("www.")) {
            return true;
        }

        for (String dominio : CatalogoGolpes.DOMINIOS_LINK) {
            if (token.endsWith(dominio)
                    || token.contains(dominio + "/")) {
                return true;
            }
        }

        return false;
    }

    private boolean dominioEhOficial(String dominio) {
        for (String dominioOficial : CatalogoGolpes.DOMINIOS_OFICIAIS) {
            if (dominio.equals(dominioOficial)
                    || dominio.endsWith("." + dominioOficial)) {
                return true;
            }
        }

        return false;
    }

    private boolean pareceMarcaProtegida(String dominio) {
        for (String marca : CatalogoGolpes.MARCAS_PROTEGIDAS) {
            if (dominio.contains(marca)
                    || distanciaEntre(dominio, marca)
                    <= limiteDeDiferencas(marca)) {
                return true;
            }
        }

        return false;
    }

    private int limiteDeDiferencas(String marca) {
        return marca.length() >= 8 ? 2 : 1;
    }

    /**
     * Calcula quantas substituições, inclusões ou remoções são necessárias
     * para transformar uma palavra na outra.
     */
    private int distanciaEntre(String primeiraPalavra, String segundaPalavra) {
        int[] anterior = new int[segundaPalavra.length() + 1];
        int[] atual = new int[segundaPalavra.length() + 1];

        for (int indice = 0; indice <= segundaPalavra.length(); indice++) {
            anterior[indice] = indice;
        }

        for (int indicePrimeira = 1;
             indicePrimeira <= primeiraPalavra.length();
             indicePrimeira++) {
            atual[0] = indicePrimeira;

            for (int indiceSegunda = 1;
                 indiceSegunda <= segundaPalavra.length();
                 indiceSegunda++) {
                int custo = primeiraPalavra.charAt(indicePrimeira - 1)
                        == segundaPalavra.charAt(indiceSegunda - 1)
                        ? 0
                        : 1;

                atual[indiceSegunda] = Math.min(
                        Math.min(
                                atual[indiceSegunda - 1] + 1,
                                anterior[indiceSegunda] + 1
                        ),
                        anterior[indiceSegunda - 1] + custo
                );
            }

            int[] temporario = anterior;
            anterior = atual;
            atual = temporario;
        }

        return anterior[segundaPalavra.length()];
    }

    private String removerPontuacaoExterna(String token) {
        return token.replaceAll(
                "^[('\"\\[]+|[.),:;!?'\"\\]]+$",
                ""
        );
    }
}
