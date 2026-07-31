package Modelos;

import java.util.ArrayList;
import java.util.HashMap;

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
        DeclaracoesCondicionais condicoes = new DeclaracoesCondicionais(mensagemNormalizada);
        Execucaoif execucao = new Execucaoif();

        pontuacao += execucao.verificacao(condicoes.urgencia,2,
                "A mensagem tenta criar urgência.", motivos);

        pontuacao += execucao.verificacao(condicoes.contemPremio,2,
                "A mensagem oferece prêmio ou dinheiro fácil.", motivos);

        pontuacao += execucao.verificacao(condicoes.dadosPessoais,4,
                "A mensagem solicita dados pessoais ou bancários.", motivos);

        pontuacao += execucao.verificacao(condicoes.contemLink,3,
                "A mensagem contém um link.", motivos);

        pontuacao += execucao.verificacao(condicoes.transferenciaSuspeita, 4,
                "A mensagem descreve uma transferência inesperada e merece verificação.", motivos);

        pontuacao += execucao.verificacao(condicoes.contemPromessaDinheiro && condicoes.contemLink,4,
            "A mensagem combina um link com uma promessa de dinheiro.", motivos);

        pontuacao += execucao.verificacao(condicoes.pedidoAcao, 2,
                "A mensagem solicita que a pessoa realize uma ação.", motivos);


        if (analisadorConteudo.contemAlgumTermo(
                mensagemNormalizada,
                CatalogoTermosGolpe.ARQUIVOS_PERIGOSOS
        )) {
            pontuacao += 7;
            motivos.add("A mensagem contém um arquivo que pode executar programas maliciosos.");
        }


        if (!transferenciaSuspeita && !declaracaoEnvioLegitimo && analisadorConteudo.contemAlgumTermo(
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
        if (!transferenciaSuspeita && !declaracaoEnvioLegitimo && (
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


        if (!transferenciaSuspeita && !declaracaoEnvioLegitimo && analisadorConteudo.contemPedidoDeValor(mensagemNormalizada)) {
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
