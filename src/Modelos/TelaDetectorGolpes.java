package Modelos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class TelaDetectorGolpes extends JFrame {
    private static final long serialVersionUID = 1L;

    private final transient DetectorGolpes detector = new DetectorGolpes();

    private final JTextArea campoMensagem = new JTextArea();
    private final JLabel textoClassificacao = new JLabel(
            "Aguardando análise"
    );
    private final JLabel textoPontuacao = new JLabel(
            "Pontuação: 0"
    );
    private final JTextArea campoMotivos = new JTextArea();

    public TelaDetectorGolpes() {
        configurarJanela();
        montarInterface();
    }

    private void configurarJanela() {
        setTitle("Detector de Golpes Digitais");
        setSize(750, 650);
        setMinimumSize(new Dimension(650, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void montarInterface() {
        JPanel painelPrincipal = new JPanel(
                new BorderLayout(15, 15)
        );

        painelPrincipal.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        painelPrincipal.add(
                criarCabecalho(),
                BorderLayout.NORTH
        );

        painelPrincipal.add(
                criarAreaCentral(),
                BorderLayout.CENTER
        );

        painelPrincipal.add(
                criarPainelResultado(),
                BorderLayout.SOUTH
        );

        setContentPane(painelPrincipal);
    }

    private JPanel criarCabecalho() {
        JPanel painel = new JPanel();
        painel.setLayout(
                new BoxLayout(painel, BoxLayout.Y_AXIS)
        );

        JLabel titulo = new JLabel(
                "DETECTOR DE GOLPES DIGITAIS"
        );

        titulo.setFont(
                new Font(
                        Font.SANS_SERIF,
                        Font.BOLD,
                        24
                )
        );

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel(
                "Cole uma mensagem para verificar possíveis sinais de fraude."
        );

        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(8));
        painel.add(subtitulo);

        return painel;
    }

    private JPanel criarAreaCentral() {
        JPanel painel = new JPanel(
                new BorderLayout(10, 10)
        );

        JLabel rotulo = new JLabel(
                "Mensagem para análise:"
        );

        campoMensagem.setLineWrap(true);
        campoMensagem.setWrapStyleWord(true);
        campoMensagem.setFont(
                new Font(
                        Font.SANS_SERIF,
                        Font.PLAIN,
                        16
                )
        );

        JScrollPane rolagemMensagem =
                new JScrollPane(campoMensagem);

        JPanel painelBotoes = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        JButton botaoAnalisar = new JButton(
                "Analisar mensagem"
        );

        JButton botaoLimpar = new JButton(
                "Limpar"
        );

        botaoAnalisar.addActionListener(
                evento -> analisarMensagem()
        );

        botaoLimpar.addActionListener(
                evento -> limparTela()
        );

        painelBotoes.add(botaoAnalisar);
        painelBotoes.add(botaoLimpar);

        painel.add(rotulo, BorderLayout.NORTH);
        painel.add(
                rolagemMensagem,
                BorderLayout.CENTER
        );
        painel.add(
                painelBotoes,
                BorderLayout.SOUTH
        );

        return painel;
    }

    private JPanel criarPainelResultado() {
        JPanel painel = new JPanel(
                new BorderLayout(10, 10)
        );

        painel.setPreferredSize(
                new Dimension(700, 230)
        );

        JPanel painelResumo = new JPanel(
                new GridLayout(2, 1, 5, 5)
        );

        textoClassificacao.setFont(
                new Font(
                        Font.SANS_SERIF,
                        Font.BOLD,
                        18
                )
        );

        textoPontuacao.setFont(
                new Font(
                        Font.SANS_SERIF,
                        Font.PLAIN,
                        16
                )
        );

        painelResumo.add(textoClassificacao);
        painelResumo.add(textoPontuacao);

        campoMotivos.setEditable(false);
        campoMotivos.setLineWrap(true);
        campoMotivos.setWrapStyleWord(true);
        campoMotivos.setFont(
                new Font(
                        Font.SANS_SERIF,
                        Font.PLAIN,
                        15
                )
        );

        JScrollPane rolagemMotivos =
                new JScrollPane(campoMotivos);

        rolagemMotivos.setBorder(
                BorderFactory.createTitledBorder(
                        "Sinais encontrados"
                )
        );

        painel.add(
                painelResumo,
                BorderLayout.NORTH
        );

        painel.add(
                rolagemMotivos,
                BorderLayout.CENTER
        );

        return painel;
    }

    private void analisarMensagem() {
        String mensagem = campoMensagem.getText();

        if (mensagem == null || mensagem.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite ou cole uma mensagem antes de analisar.",
                    "Mensagem vazia",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {
            ResultadoAnalise resultado =
                    detector.analisar(mensagem);

            exibirResultado(resultado);

        } catch (IllegalArgumentException excecao) {
            JOptionPane.showMessageDialog(
                    this,
                    excecao.getMessage(),
                    "Não foi possível analisar",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocorreu um erro durante a análise.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void exibirResultado(
            ResultadoAnalise resultado
    ) {
        textoClassificacao.setText(
                "Resultado: " + resultado.nivelRisco()
        );

        textoPontuacao.setText(
                "Pontuação: " + resultado.pontuacao()
        );

        alterarCorClassificacao(
                resultado.pontuacao()
        );

        if (resultado.motivos().isEmpty()) {
            campoMotivos.setText(
                    "Nenhum sinal de golpe foi encontrado."
            );

            return;
        }

        StringBuilder motivosFormatados =
                new StringBuilder();

        for (String motivo : resultado.motivos()) {
            motivosFormatados
                    .append("• ")
                    .append(motivo)
                    .append(System.lineSeparator());
        }

        campoMotivos.setText(
                motivosFormatados.toString()
        );

        campoMotivos.setCaretPosition(0);
    }

    private void alterarCorClassificacao(int pontuacao) {
        if (pontuacao >= 7) {
            textoClassificacao.setForeground(
                    new Color(180, 30, 30)
            );

            return;
        }

        if (pontuacao >= 4) {
            textoClassificacao.setForeground(
                    new Color(210, 120, 0)
            );

            return;
        }

        textoClassificacao.setForeground(
                new Color(20, 130, 70)
        );
    }

    private void limparTela() {
        campoMensagem.setText("");
        campoMotivos.setText("");

        textoClassificacao.setText(
                "Aguardando análise"
        );

        textoClassificacao.setForeground(
                UIManager.getColor("Label.foreground")
        );

        textoPontuacao.setText(
                "Pontuação: 0"
        );

        campoMensagem.requestFocusInWindow();
    }
}
