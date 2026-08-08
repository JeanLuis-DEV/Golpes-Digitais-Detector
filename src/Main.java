import Modelos.TelaDetectorGolpes;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaDetectorGolpes tela = new TelaDetectorGolpes();
            tela.setVisible(true);
        });
    }
}