import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {

    public static void main(String[] args) {
       // Poner la UI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
        // Opcional: setear el look & feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        TableroUI ui = new TableroUI();
            ui.setVisible(true);
        });
    }
}