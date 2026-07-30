package modelos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Coordena a análise executando automaticamente todas as regras cadastradas.
public final class DetectorGolpes implements AnalisadorMensagem {
    private final NormalizadorMensagem normalizador;
    private final AnalisadorConteudoMensagem analisadorConteudo;
    private final AnalisadorTransferencia analisadorTransferencia;
    private final ClassificadorRisco classificadorRisco;
    private final List<RegraAnalise> regras;

    public DetectorGolpes() {
        normalizador = new NormalizadorMensagem();
        analisadorConteudo = new AnalisadorConteudoMensagem();
        analisadorTransferencia = new AnalisadorTransferencia(analisadorConteudo);
        classificadorRisco = new ClassificadorRisco();
        regras = CatalogoTermosGolpe.criarRegras(
                analisadorConteudo,
                analisadorTransferencia
        );
    }

    @Override
    public ResultadoAnalise analisar(String mensagem) {
        validarMensagem(mensagem);

        ContextoAnalise contexto = new ContextoAnalise(
                normalizador.normalizar(mensagem)
        );

        List<ResultadoRegra> resultados = regras.stream()
                .map(regra -> regra.avaliar(contexto))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        int pontuacao = resultados.stream()
                .mapToInt(ResultadoRegra::getPontuacao)
                .sum();

        List<String> motivos = resultados.stream()
                .map(ResultadoRegra::getMotivo)
                .collect(Collectors.toList());

        return new ResultadoAnalise(
                classificadorRisco.classificar(pontuacao),
                pontuacao,
                motivos
        );
    }

    private void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("A mensagem não pode estar vazia.");
        }
    }
}
