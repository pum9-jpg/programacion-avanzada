import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VentanaUI extends JFrame {
    
    public VentanaUI() {
        super("Buscaminas");
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        int DIMENSION = 10;
        int[][] terreno = new int[DIMENSION][DIMENSION];

        //Crear minas
        Random random = new Random();
        int CANTIDAD_MINAS = (int)Math.floor(DIMENSION*DIMENSION*0.3) ;
        for (int i = 0; i < CANTIDAD_MINAS; i++) {
            terreno[random.nextInt(DIMENSION)][random.nextInt(DIMENSION)]= -1;
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

        // Filas 0..n-1 (n columnas)
        for (int row = 0; row < terreno.length; row++) {
            for (int col = 0; col < terreno[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                int aux = terreno[row][col];
                JButton b = makeButton(aux, buttonFont);
                // Placeholder: aquí podrías añadir ActionListeners para cada botón
                buttonsPanel.add(b, gbc);
            }
        }

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 800));
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private JButton makeButton(int valor, Font font) {
        Celda b = new Celda(valor);
        b.setFont(font);
        b.setPreferredSize(new Dimension(25, 25));
        b.setFocusable(false);
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        b.addActionListener(e -> handleButtonClick((Celda) e.getSource()));

        return b;
    }

    private void handleButtonClick(Celda boton) {
        int aux = boton.getValor();
        if (aux == -1)
            boton.setText("💥");
        else
            boton.setText(Integer.toString(aux));
    }

}