import javax.swing.*;
import java.awt.*;

public class TableroUI extends JFrame {

    private boolean turno; // X true y O false

    private String[][] labelsString = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
        };
    
    public TableroUI() {
        super("Calculadora");
        this.turno = true;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        // Fuente y dimensiones recomendadas
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Filas 0..3 (4 columnas)
        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                JButton b = makeButton(labelsString[row][col], buttonFont);
                // Placeholder: aquí podrías añadir ActionListeners para cada botón
                buttonsPanel.add(b, gbc);
            }
        }

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private JButton makeButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setPreferredSize(new Dimension(50, 50));
        b.setFocusable(false);
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        b.addActionListener(e -> handleButtonClick((JButton) e.getSource()));

        return b;
    }

    private void handleButtonClick(JButton boton) {
        if (this.turno) {
            boton.setText("X");
        } else {
            boton.setText("O");
        }

        this.turno = !this.turno;
    }

}