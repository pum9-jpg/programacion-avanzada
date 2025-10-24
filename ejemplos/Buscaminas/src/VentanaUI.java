import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD

public class VentanaUI extends JFrame {
    
    private Celda[][] botones;
    private final int DIMENSION = 10;
    private Terreno terreno;

    // Control de juego
    private boolean juegoActivo;
    private int celdasReveladas;
    private JButton resetButton;
    
    public VentanaUI() {
        super("Buscaminas");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        iniciarJuego();
        
        pack();
        setLocationRelativeTo(null); 
    }
    
    private void iniciarJuego() {
        terreno = new Terreno(DIMENSION);
        botones = new Celda[DIMENSION][DIMENSION];
        this.juegoActivo = true;
        this.celdasReveladas = 0;
        
        buildUI();
    }

    private void buildUI() {
        if (getContentPane() != null) {
            getContentPane().removeAll();
        }
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Botón de Reinicio
        resetButton = new JButton("Reiniciar Juego");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> reiniciarJuego()); 
        mainPanel.add(resetButton, BorderLayout.NORTH);
=======
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
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f

        JPanel buttonsPanel = crearPanelBotones();

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 800));
<<<<<<< HEAD
        revalidate();
        repaint();
    }
    
    private void reiniciarJuego() {
        iniciarJuego();
    }

    private Celda makeButton(int valor, Font font, int row, int col) {
=======
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private Celda makeButton(int valor, Font font) {
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
        Celda b = new Celda(valor);
        b.setFont(font);
        b.setPreferredSize(new Dimension(25, 25));
        b.setFocusable(false);
<<<<<<< HEAD
        // Guardar las coordenadas para usar en la lógica
        b.addActionListener(e -> handleButtonClick(b, row, col)); 
        return b;
    }

    private void handleButtonClick(Celda boton, int row, int col) {
        if (!juegoActivo || boton.getEsVisible()) return;

        int valor = boton.getValor();
        boton.setEsVisible(true);
=======
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        b.addActionListener(e -> handleButtonClick((Celda) e.getSource()));

        return b;
    }

    private void handleButtonClick(Celda boton) {
        int valor = boton.getValor();
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f

        if (valor == -1) {
            boton.setText("💥");
            boton.setBackground(Color.RED);
<<<<<<< HEAD
            terminarJuego(false);
        } else {
            boton.setEnabled(false);
            celdasReveladas++;
            
            if (valor == 0) {
                boton.setText("");
                boton.setBackground(Color.LIGHT_GRAY);
                // NOTA: Aquí se llama a la propagación si se desea
=======

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
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
            } else {
                boton.setText(Integer.toString(valor));
                boton.setForeground(getColorPorNumero(valor));
                boton.setFont(new Font("Arial", Font.BOLD, 18));
            }
<<<<<<< HEAD
            
            // Verificar Victoria
            if (celdasReveladas == terreno.getCeldasSeguras()) {
                terminarJuego(true);
            }
        }
    }
    
    private void terminarJuego(boolean haGanado) {
        juegoActivo = false;
        
        mostrarTodosLosBotones(haGanado);
        
        String mensaje = haGanado ? 
            "🎉 ¡Felicidades, HAS GANADO! 🎉" : 
            "💣 ¡BOOM! Has perdido. 💣";
            
        JOptionPane.showMessageDialog(this, mensaje, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
    }
=======
        }
    }
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f

    private Color getColorPorNumero(int n) {
        switch (n) {
            case 1: return Color.BLUE;
<<<<<<< HEAD
            case 2: return new Color(0, 128, 0);
            case 3: return Color.RED;
            case 4: return new Color(0, 0, 128);
            case 5: return new Color(128, 0, 0);
            case 6: return new Color(64, 224, 208);
=======
            case 2: return new Color(0, 128, 0);     // Verde
            case 3: return Color.RED;
            case 4: return new Color(0, 0, 128);     // Azul oscuro
            case 5: return new Color(128, 0, 0);     // Marrón oscuro
            case 6: return new Color(64, 224, 208);  // Turquesa
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
            case 7: return Color.BLACK;
            case 8: return Color.GRAY;
            default: return Color.BLACK;
        }
    }

<<<<<<< HEAD
    private void mostrarTodosLosBotones(boolean forzarActivacion) {
=======
    private void mostrarTodosLosBotones() {
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                Celda boton = botones[row][col];
                int valor = boton.getValor();
                
<<<<<<< HEAD
                if (forzarActivacion) {
                    // Si gana, solo desactivar
                    boton.setEnabled(false);
                    continue;
                }
                
                if (valor == -1) {
                    boton.setText("💣");
                    boton.setBackground(Color.ORANGE);
                } else if (valor == 0) {
                    boton.setText("");
                    boton.setBackground(Color.LIGHT_GRAY);
                } else {
=======
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
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
                    boton.setText(Integer.toString(valor));
                    boton.setForeground(getColorPorNumero(valor));
                    boton.setBackground(Color.LIGHT_GRAY);
                }
<<<<<<< HEAD
                boton.setEnabled(false); 
=======
                boton.setEnabled(false); // Deshabilitar el botón
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
            }
        }
    }

    private JPanel crearPanelBotones() {
<<<<<<< HEAD
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        JPanel panel = new JPanel(new GridLayout(DIMENSION, DIMENSION, 2, 2)); // Cambiado a GridLayout
        
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                int aux = terreno.getValor(row, col);
                // Le pasamos las coordenadas para el listener
                Celda b = makeButton(aux, buttonFont, row, col); 
                botones[row][col] = b;
                panel.add(b);
            }
        }
=======
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

>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
        return panel;
    }
}