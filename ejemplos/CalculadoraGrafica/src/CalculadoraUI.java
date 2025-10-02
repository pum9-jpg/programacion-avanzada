import javax.swing.*;
import java.awt.*;

public class CalculadoraUI extends JFrame {

    private final JTextField display;
    
    public CalculadoraUI() {
        super("Calculadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        // Fuente y dimensiones recomendadas
        Font displayFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Display (pantalla) - no editable por teclado, alineado a la derecha
        display = new JTextField("0");
        display.setEditable(true);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(displayFont);
        display.setPreferredSize(new Dimension(260, 60));

        mainPanel.add(display, BorderLayout.NORTH);

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        String[][] labelsString = {
            {"C", "±", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"}
        };

        // Filas 0..3 (4 columnas)
        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                JButton b = makeButton(labelsString[row][col], buttonFont);
                // Placeholder: aquí podrías añadir ActionListeners para cada botón
                buttons.add(b, gbc);
            }
        }

        // Última fila: 0 (dos columnas), ., =
        // Botón 0 que ocupa dos columnas
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton zero = makeButton("0", buttonFont);
        buttons.add(zero, gbc);


        // Botón '.'
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JButton dot = makeButton(".", buttonFont);
        buttons.add(dot, gbc);


        // Botón '='
        gbc.gridx = 3;
        gbc.gridy = 4;
        JButton eq = makeButton("=", buttonFont);
        buttons.add(eq, gbc);

        mainPanel.add(buttons, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private JButton makeButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setFocusable(false);
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        // b.addActionListener(e -> {
        // // TODO: manejar eventos del botón
        // });
        return b;
    }

}