package modelos;

// Contrato para qualquer componente capaz de analisar uma mensagem.
public interface AnalisadorMensagem {
    ResultadoAnalise analisar(String mensagem);
}
