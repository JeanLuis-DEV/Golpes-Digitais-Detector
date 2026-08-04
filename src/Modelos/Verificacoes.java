package Modelos;

import java.util.Map;

public class Verificacoes {
    VerificadorMotivo execucao;
    MapMotivos motivos = Map.of("urgencia", "A mensagem tenta criar urgência.",
        ("contemPremio", "A mensagem oferece prêmio ou dinheiro fácil.",
        ("dadosPessoais", "A mensagem solicita dados pessoais ou bancários.",
        ("contemLink", "A mensagem contém um link.",
        ("transferenciaSuspeita", "A mensagem descreve uma transferência inesperada e merece verificação.",
        ("promessaDinheiro_e_link", "A mensagem combina um link com uma promessa de dinheiro.",
        ("pedidoAcao", "A mensagem solicita que a pessoa realize uma ação.",
        ("arquivosPerigosos", "A mensagem contém um arquivo que pode executar programas maliciosos.",
        ("solicitaTransferencia", "A mensagem solicita uma transferência de dinheiro.",
        ("mudancaContato", "A mensagem informa uma mudança inesperada de contato.",
        ("pedidoDinheiro", "A mensagem contém um pedido de dinheiro.",
        ("pagamentoParaTerceiros", "O pagamento solicitado seria enviado para outra pessoa.",
        ("ameacaBloqueio", "A mensagem ameaça bloquear ou suspender um serviço.",
        ("falsaInstituicao", "A mensagem tenta se apresentar como uma instituição ou suporte.",
        ("taxaAntecipada", "A mensagem cobra um valor antecipado para liberar algo.",
        ("investimentoSuspeito", "A mensagem promete retorno financeiro fácil ou garantido.",
        ("acessoRemoto", "A mensagem solicita acesso remoto ao dispositivo.",
        ("pedidosValor", "A mensagem menciona um valor em dinheiro.",
        ("pixEngano", "A mensagem solicita retorno de um valor enviado a ele por pix.",
        ("verificacaoIdentidade", "A mensagem solicita enviar dados sensíveis para verificação",
        ("pressaoTemporalPix", "A mensagem solicita urgência mediante legitimidade",
        ("ameacaInformal", "A mensagem solicita urgência mediante ameaça",
        ("justificativaSuspeita", "A mensagem tenta passar legitimidade através de desculpa financeira",
        ("solicitacaoConfirmacaoImediata", "A mensagem solicita urgência sem tempo de análise ou confirmação",
        ("comparecerEndereco", "A mensagem solicita o comparecimento em local que deve ser verificado pelo usuário");
            


    public Verificacoes(VerificadorMotivo execucao) {
        this.execucao = execucao;
    }

    public int contemTermos(String mensagemNormalizada) {

        int pontuacao = 0;
        DeclaracoesCondicionais condicoes = new DeclaracoesCondicionais(mensagemNormalizada);

        pontuacao += execucao.verificacao(condicoes.urgencia, 2,
                motivos.getListaMotivos("urgencia"));

        pontuacao += execucao.verificacao(condicoes.contemPremio, 2,
                motivos.getListaMotivos("contemPremio"));

        pontuacao += execucao.verificacao(condicoes.dadosPessoais, 4,
                motivos.getListaMotivos("dadosPessoais"));

        pontuacao += execucao.verificacao(condicoes.contemLink, 3,
                motivos.getListaMotivos("contemLink"));

        pontuacao += execucao.verificacao(condicoes.transferenciaSuspeita, 4,
                motivos.getListaMotivos("transferenciaSuspeita"));

        pontuacao += execucao.verificacao(condicoes.contemPromessaDinheiro && condicoes.contemLink, 4,
                motivos.getListaMotivos("promessaDinheiro_e_link"));

        pontuacao += execucao.verificacao(condicoes.pedidoAcao, 2,
                motivos.getListaMotivos("pedidoAcao"));

        pontuacao += execucao.verificacao(condicoes.arquivosPerigosos, 7,
                motivos.getListaMotivos("arquivosPerigosos"));

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo && condicoes.termosTransferencias,
                3, motivos.getListaMotivos("solicitaTransferencia"));

        pontuacao += execucao.verificacao(condicoes.mudancaContato, 2,
                motivos.getListaMotivos("mudancaContato"));

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo
                && condicoes.pedidosValor && condicoes.pedidosDinheiro, 4, motivos.getListaMotivos("pedidoDinheiro"));

        pontuacao += execucao.verificacao(condicoes.pagamentoParaTerceiros, 2,
                motivos.getListaMotivos("pagamentoParaTerceiros"));

        pontuacao += execucao.verificacao(condicoes.ameacaBloqueio, 4,
                motivos.getListaMotivos("ameacaBloqueio"));

        pontuacao += execucao.verificacao(condicoes.falsaInstituicao, 2,
                motivos.getListaMotivos("falsaInstituicao"));

        pontuacao += execucao.verificacao(condicoes.taxaAntecipada, 4,
                motivos.getListaMotivos("taxaAntecipada"));

        pontuacao += execucao.verificacao(condicoes.investimentoSuspeito, 4,
                motivos.getListaMotivos("investimentoSuspeito"));

        pontuacao += execucao.verificacao(condicoes.acessoRemoto, 7,
                motivos.getListaMotivos("acessoRemoto"));

        pontuacao += execucao.verificacao(!condicoes.transferenciaSuspeita && !condicoes.declaracaoEnvioLegitimo
                && condicoes.pedidosValor, 2, motivos.getListaMotivos("pedidosValor"));

        pontuacao += execucao.verificacao(condicoes.pixEngano, 4,
                motivos.getListaMotivos("pixEngano"));

        pontuacao += execucao.verificacao(condicoes.verificacaoIdentidade, 4,
                motivos.getListaMotivos("verificacaoIdentidade"));

        pontuacao += execucao.verificacao(condicoes.pressaoTemporalPix, 4,
                motivos.getListaMotivos("pressaoTemporalPix"));

        pontuacao += execucao.verificacao(condicoes.ameacaInformal, 4,
                motivos.getListaMotivos("ameacaInformal"));

        pontuacao += execucao.verificacao(condicoes.justificativaSuspeita, 4,
                motivos.getListaMotivos("justificativaSuspeita"));

        pontuacao += execucao.verificacao(condicoes.solicitacaoConfirmacaoImediata, 4,
                motivos.getListaMotivos("solicitacaoConfirmacaoImediata"));

        pontuacao += execucao.verificacao(condicoes.comparecerEndereco, 2,
                motivos.getListaMotivos("comparecerEndereco"));

        return pontuacao;
    }
}
