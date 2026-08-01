package Modelos;

import java.util.HashMap;

public class MapMotivos {
    private HashMap<String, String> listaMotivos = new HashMap<>();

    public MapMotivos() {
        listaMotivos.put("urgencia", "A mensagem tenta criar urgência.");
        listaMotivos.put("contemPremio", "A mensagem oferece prêmio ou dinheiro fácil.");
        listaMotivos.put("dadosPessoais", "A mensagem solicita dados pessoais ou bancários.");
        listaMotivos.put("contemLink", "A mensagem contém um link.");
        listaMotivos.put("transferenciaSuspeita", "A mensagem descreve uma transferência inesperada e merece verificação.");
        listaMotivos.put("promessaDinheiro_e_link", "A mensagem combina um link com uma promessa de dinheiro.");
        listaMotivos.put("pedidoAcao", "A mensagem solicita que a pessoa realize uma ação.");
        listaMotivos.put("arquivosPerigosos", "A mensagem contém um arquivo que pode executar programas maliciosos.");
        listaMotivos.put("solicitaTransferencia", "A mensagem solicita uma transferência de dinheiro.");
        listaMotivos.put("mudancaContato", "A mensagem informa uma mudança inesperada de contato.");
        listaMotivos.put("pedidoDinheiro", "A mensagem contém um pedido de dinheiro.");
        listaMotivos.put("pagamentoParaTerceiros", "O pagamento solicitado seria enviado para outra pessoa.");
        listaMotivos.put("ameacaBloqueio", "A mensagem ameaça bloquear ou suspender um serviço.");
        listaMotivos.put("falsaInstituicao", "A mensagem tenta se apresentar como uma instituição ou suporte.");
        listaMotivos.put("taxaAntecipada", "A mensagem cobra um valor antecipado para liberar algo.");
        listaMotivos.put("investimentoSuspeito", "A mensagem promete retorno financeiro fácil ou garantido.");
        listaMotivos.put("acessoRemoto", "A mensagem solicita acesso remoto ao dispositivo.");
        listaMotivos.put("pedidosValor", "A mensagem menciona um valor em dinheiro.");
    }
    public String getListaMotivos(String chave) {
        return listaMotivos.get(chave);
    }
}
