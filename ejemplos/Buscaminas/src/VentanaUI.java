import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VentanaUI extends JFrame {

    
    public VentanaUI() {
        super("Buscaminas");
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1600, 1600);

        int DIMENSION = 10;
        int[][] terreno = new int[DIMENSION][DIMENSION];

        //Crear minas
        Random random = new Random();
        int CANTIDAD_MINAS = (int)Math.floor(DIMENSION*DIMENSION*0.3) ;
        for (int i = 0; i < CANTIDAD_MINAS; i++) {
            terreno[random.nextInt(DIMENSION)][random.nextInt(10)] = -1;
        }

        // Fuente y dimensiones recomendadas
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Filas 0..3 (4 columnas)
        for (int row = 0; row < terreno.length; row++) {
            for (int col = 0; col < terreno[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                int valor = terreno[row][col];
                JButton b = makeButton(Integer.toString(valor), buttonFont);
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
        b.setPreferredSize(new Dimension(25, 25));
        b.setFocusable(false);
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        b.addActionListener(e -> handleButtonClick((JButton) e.getSource()));

        return b;
    }

    private void handleButtonClick(JButton boton) {
        
    }

}