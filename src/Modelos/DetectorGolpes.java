package Modelos;

import java.util.ArrayList;

// Coordena a análise da mensagem e aplica as regras de pontuação.
public class DetectorGolpes {
    private final NormalizadorMensagem normalizador = new NormalizadorMensagem();
    private final AnalisadorConteudoMensagem analisadorConteudo = new AnalisadorConteudoMensagem();
    private final ClassificadorRisco classificadorRisco = new ClassificadorRisco();

    // Analisa uma mensagem e devolve sua pontuação, classificação e motivos.
    public ResultadoAnalise analisar(String mensagem) {
        validarMensagem(mensagem);

        String mensagemNormalizada = normalizador.normalizar(mensagem);
        int pontuacao = 0;
        ArrayList<String> motivos = new ArrayList<>();

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_URGENCIA
        )) {
            pontuacao += 2;
            motivos.add("A mensagem tenta criar urgência.");
        }

        boolean contemPremio = analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_PREMIO
        );

        if (contemPremio) {
            pontuacao += 2;
            motivos.add("A mensagem oferece prêmio ou dinheiro fácil.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_DADOS_PESSOAIS
        )) {
            pontuacao += 4;
            motivos.add("A mensagem solicita dados pessoais ou bancários.");
        }

        boolean contemLink = analisadorConteudo.contemLink(mensagemNormalizada);

        if (contemLink) {
            pontuacao += 3;
            motivos.add("A mensagem contém um link.");
        }

        boolean contemPromessaDinheiro = contemPremio
                || analisadorConteudo.contemAlgumTermo(
                        mensagemNormalizada,
                        CatalogoTermosGolpe.TERMOS_PROMESSA_DINHEIRO
                );
        boolean declaracaoEnvioLegitimo = analisadorConteudo.contemDeclaracaoEnvioLegitimo(mensagemNormalizada);

        if (contemPromessaDinheiro && contemLink) {
            pontuacao += 4;
            motivos.add("A mensagem combina um link com uma promessa de dinheiro.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.PEDIDOS_DE_ACAO
        )) {
            pontuacao += 2;
            motivos.add("A mensagem solicita que a pessoa realize uma ação.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.ARQUIVOS_PERIGOSOS
        )) {
            pontuacao += 7;
            motivos.add("A mensagem contém um arquivo que pode executar programas maliciosos.");
        }

        if (!declaracaoEnvioLegitimo && analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_TRANSFERENCIA
        )) {
            pontuacao += 3;
            motivos.add("A mensagem solicita uma transferência de dinheiro.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_MUDANCA_CONTATO
        )) {
            pontuacao += 2;
            motivos.add("A mensagem informa uma mudança inesperada de contato.");
        }

        // Um pedido explícito de dinheiro já exige cautela mesmo sem outros sinais.
        if (!declaracaoEnvioLegitimo && (
                analisadorConteudo.contemAlgumTermo(
                        mensagemNormalizada,
                        CatalogoTermosGolpe.PEDIDOS_DE_DINHEIRO
                ) || analisadorConteudo.contemPedidoDeValor(mensagemNormalizada)
        )) {
            pontuacao += 4;
            motivos.add("A mensagem contém um pedido de dinheiro.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.PAGAMENTO_PARA_TERCEIROS
        )) {
            pontuacao += 2;
            motivos.add("O pagamento solicitado seria enviado para outra pessoa.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_AMEACA_OU_BLOQUEIO
        )) {
            pontuacao += 4;
            motivos.add("A mensagem ameaça bloquear ou suspender um serviço.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_FALSA_INSTITUICAO
        )) {
            pontuacao += 2;
            motivos.add("A mensagem tenta se apresentar como uma instituição ou suporte.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_TAXA_ANTECIPADA
        )) {
            pontuacao += 4;
            motivos.add("A mensagem cobra um valor antecipado para liberar algo.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_INVESTIMENTO_SUSPEITO
        )) {
            pontuacao += 4;
            motivos.add("A mensagem promete retorno financeiro fácil ou garantido.");
        }

        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.TERMOS_ACESSO_REMOTO
        )) {
            pontuacao += 7;
            motivos.add("A mensagem solicita acesso remoto ao dispositivo.");
        }


        if (!declaracaoEnvioLegitimo && analisadorConteudo.contemPedidoDeValor(mensagemNormalizada)) {
            pontuacao += 2;
            motivos.add("A mensagem menciona um valor em dinheiro.");
        }

        String nivelRisco = classificadorRisco.classificar(pontuacao);
        return new ResultadoAnalise(nivelRisco, pontuacao, motivos);
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }
    }

}
