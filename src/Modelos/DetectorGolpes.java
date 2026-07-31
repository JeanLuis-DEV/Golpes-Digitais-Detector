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

        pontuacao += execucao.verificacao(condicoes.arquivosPerigosos, 7,
                "A mensagem contém um arquivo que pode executar programas maliciosos.", motivos);

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo && condicoes.termosTransferencias,
                3, "A mensagem solicita uma transferência de dinheiro.", motivos);

        pontuacao += execucao.verificacao(condicoes.mudancaContato, 2,
                "A mensagem informa uma mudança inesperada de contato.", motivos);

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo
        && (condicoes.pedidosDinheiro || condicoes.pedidosValor), 4, "A mensagem contém um pedido de dinheiro.",
                motivos);

        pontuacao += execucao.verificacao(condicoes.pagamentoParaTerceiros, 2,
                "O pagamento solicitado seria enviado para outra pessoa.", motivos);

        pontuacao += execucao.verificacao(condicoes.ameacaBloqueio, 4,
                "A mensagem ameaça bloquear ou suspender um serviço.", motivos);

        pontuacao += execucao.verificacao(condicoes.falsaInstituicao, 2,
                "A mensagem tenta se apresentar como uma instituição ou suporte.", motivos);

        pontuacao += execucao.verificacao(condicoes.taxaAntecipada, 4,
                "A mensagem cobra um valor antecipado para liberar algo.", motivos);

        pontuacao += execucao.verificacao(condicoes.investimentoSuspeito, 4,
                "A mensagem promete retorno financeiro fácil ou garantido.", motivos);

        pontuacao += execucao.verificacao(condicoes.acessoRemoto, 7,
                "A mensagem solicita acesso remoto ao dispositivo.", motivos);

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo
        && condicoes.pedidosValor, 2, "A mensagem solicita acesso remoto ao dispositivo.", motivos);

        String nivelRisco = classificadorRisco.classificar(pontuacao);
        return new ResultadoAnalise(nivelRisco, pontuacao, motivos);
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }
    }

}
