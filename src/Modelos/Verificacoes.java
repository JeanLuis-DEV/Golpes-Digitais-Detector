package Modelos;

public class Verificacoes {
    Execucaoif execucao;
    MapMotivos motivos = new MapMotivos();

    public Verificacoes(Execucaoif execucao) {
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
                && (condicoes.pedidosDinheiro || condicoes.pedidosValor), 4, motivos.getListaMotivos("pedidoDinheiro"));

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

        pontuacao += execucao.verificacao(condicoes.comparecerEndereco, 4,
                motivos.getListaMotivos("comparecerEndereco"));

        return pontuacao;
    }
}
