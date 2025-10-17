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

        //Crear los contadores
        for (int row = 0; row < terreno.length; row++) {
            for (int col = 0; col < terreno[row].length; col++) {
                int aux = terreno[row][col];
                if (aux != -1) {
                    int contador = 0;

                    // Verificar los 8 vecinos con IFs
                    if (row - 1 >= 0 && terreno[row - 1][col] == -1)
                        contador++;
                    if (row + 1 < terreno.length && terreno[row + 1][col] == -1)
                        contador++;
                    if (col - 1 >= 0 && terreno[row][col - 1] == -1)
                        contador++;
                    if (col + 1 < terreno[row].length && terreno[row][col + 1] == -1)
                        contador++;
                    if (row - 1 >= 0 && col - 1 >= 0 && terreno[row - 1][col - 1] == -1)
                        contador++;
                    if (row - 1 >= 0 && col + 1 < terreno[row].length && terreno[row - 1][col + 1] == -1)
                        contador++;
                    if (row + 1 < terreno.length && col - 1 >= 0 && terreno[row + 1][col - 1] == -1)
                        contador++;
                    if (row + 1 < terreno.length && col + 1 < terreno[row].length && terreno[row + 1][col + 1] == -1)
                        contador++;

                    // Guardar el número de minas vecinas
                    terreno[row][col] = contador;
                } 
            }
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
        int valor = boton.getValor();

        if (valor == -1) {
            boton.setText("💥");
            boton.setBackground(Color.RED);
        } else {
            if (valor == 0) {
                boton.setText(""); // sin texto
                boton.setEnabled(false); // opcional, desactivar celda vacía
            } else {
                boton.setText(Integer.toString(valor));
                boton.setForeground(getColorPorNumero(valor));
                boton.setFont(new Font("Arial", Font.BOLD, 18));
            }
        }
    }

    private Color getColorPorNumero(int n) {
        switch (n) {
            case 1: return Color.BLUE;
            case 2: return new Color(0, 128, 0);     // Verde
            case 3: return Color.RED;
            case 4: return new Color(0, 0, 128);     // Azul oscuro
            case 5: return new Color(128, 0, 0);     // Marrón oscuro
            case 6: return new Color(64, 224, 208);  // Turquesa
            case 7: return Color.BLACK;
            case 8: return Color.GRAY;
            default: return Color.BLACK;
        }
    }
}