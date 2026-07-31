package Modelos;

public class DeclaracoesCondicionais {
    protected boolean urgencia;
    protected boolean contemPremio;
    protected boolean contemPromessaDinheiro;
    protected boolean contemLink;
    protected boolean declaracaoEnvioLegitimo;
    protected boolean transferenciaSuspeita;
    protected boolean dadosPessoais;
    protected boolean pedidoAcao;

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
                CatalogoTermosGolpe.TERMOS_PROMESSA_DINHEIRO
        );

        pedidoAcao = analisadorConteudo.contemAlgumTermo(
                mensagem,
                CatalogoTermosGolpe.PEDIDOS_DE_ACAO
        );
    }

}
