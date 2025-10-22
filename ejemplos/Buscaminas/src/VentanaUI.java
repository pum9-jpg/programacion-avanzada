import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VentanaUI extends JFrame {
    
    private Celda[][] botones; // Matriz para guardar referencias a todos los botones
    private int DIMENSION = 10;
    private Terreno terreno;

    public VentanaUI() {
        super("Buscaminas");
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        //int DIMENSION = 10;
        terreno = new Terreno(DIMENSION);
        botones = new Celda[DIMENSION][DIMENSION]; // Inicializar la matriz de botones

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonsPanel = crearPanelBotones();

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 800));
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private Celda makeButton(int valor, Font font) {
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

            // MOSTRAR TODOS LOS BOTONES
            mostrarTodosLosBotones();
            
            //Arroja una alerta que perdiste por pisar una mina
            JOptionPane.showMessageDialog(this, 
                "¡Perdiste! Has pisado una mina.", 
                "Fin del juego", 
                JOptionPane.ERROR_MESSAGE);
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

    private void mostrarTodosLosBotones() {
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                Celda boton = botones[row][col];
                int valor = boton.getValor();
                
                // Si ya estaba revelado, saltar
                if (!boton.isEnabled()) continue;
                
                if (valor == -1) {
                    // Es una mina
                    boton.setText("💣");
                    boton.setBackground(Color.ORANGE);
                } else if (valor == 0) {
                    // Celda vacía
                    boton.setText("");
                    boton.setBackground(Color.LIGHT_GRAY);
                } else {
                    // Número
                    boton.setText(Integer.toString(valor));
                    boton.setForeground(getColorPorNumero(valor));
                    boton.setBackground(Color.LIGHT_GRAY);
                }
                boton.setEnabled(false); // Deshabilitar el botón
            }
        }
    }

    private JPanel crearPanelBotones() {
        // Fuente y dimensiones recomendadas
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Filas 0..n-1 (n columnas)
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                int aux = terreno.getValor(row, col);
                Celda b = makeButton(aux, buttonFont);
                botones[row][col]=b;                // Placeholder: aquí podrías añadir ActionListeners para cada botón
                panel.add(b, gbc);
            }
        }

        return panel;
    }
}