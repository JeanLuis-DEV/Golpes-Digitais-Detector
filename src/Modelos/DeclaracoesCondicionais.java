package Modelos;

import javax.xml.catalog.Catalog;

public class DeclaracoesCondicionais {
    protected boolean urgencia;
    protected boolean contemPremio;
    protected boolean contemPromessaDinheiro;
    protected boolean contemLink;
    protected boolean declaracaoEnvioLegitimo;
    protected boolean transferenciaSuspeita;
    protected boolean dadosPessoais;
    protected boolean pedidoAcao;
    protected boolean arquivosPerigosos;
    protected boolean termosTransferencias;
    protected boolean mudancaContato;
    protected boolean pedidosDinheiro;
    protected boolean pedidosValor;
    protected boolean pagamentoParaTerceiros;
    protected boolean ameacaBloqueio;
    protected boolean falsaInstituicao;
    protected boolean taxaAntecipada;
    protected boolean investimentoSuspeito;
    protected boolean acessoRemoto;
    protected boolean pixEngano;
    protected boolean verificacaoIdentidade;
    protected boolean pressaoTemporalPix;
    protected boolean ameacaInformal;
    protected boolean justificativaSuspeita;
    protected boolean solicitacaoConfirmacaoImediata;

    public DeclaracoesCondicionais(String mensagem) {
        AnalisadorConteudoMensagem analisadorConteudo = new AnalisadorConteudoMensagem();
        urgencia = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_URGENCIA);

        contemPremio = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_PREMIO
        );
        dadosPessoais = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_DADOS_PESSOAIS);

        contemLink = analisadorConteudo.contemLink(mensagem);

        declaracaoEnvioLegitimo = analisadorConteudo.contemDeclaracaoEnvioLegitimo(mensagem);

        transferenciaSuspeita = analisadorConteudo.contemTransferenciaSuspeita(mensagem);

        contemPromessaDinheiro = contemPremio
                || analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_PROMESSA_DINHEIRO);

        pedidoAcao = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.PEDIDOS_DE_ACAO);

        arquivosPerigosos = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.ARQUIVOS_PERIGOSOS);

        termosTransferencias = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_TRANSFERENCIA);

        mudancaContato = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_MUDANCA_CONTATO);

        pedidosDinheiro = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.PEDIDOS_DE_DINHEIRO);

        pedidosValor = analisadorConteudo.contemPedidoDeValor(mensagem);

        pagamentoParaTerceiros = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.PAGAMENTO_PARA_TERCEIROS);

        ameacaBloqueio = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_AMEACA_OU_BLOQUEIO);

        falsaInstituicao = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_FALSA_INSTITUICAO);

        taxaAntecipada = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_TAXA_ANTECIPADA);

        investimentoSuspeito = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_INVESTIMENTO_SUSPEITO);

        acessoRemoto = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_ACESSO_REMOTO);

        pixEngano = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_PIX_ENGANO
        );

        verificacaoIdentidade = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_VERIFICACAO_IDENTIDADE);

        pressaoTemporalPix = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_PRESSAO_TEMPORAL_PIX);

        ameacaInformal = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_AMEACA_INFORMAL
        );

        justificativaSuspeita = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_JUSTIFICATIVA_SUSPEITA
        );

        solicitacaoConfirmacaoImediata = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.TERMOS_SOLICITACAO_CONFIRMACAO_IMEDIATA
        );


    }
}
