import javax.swing.*;
import java.awt.*;

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

        JPanel buttonsPanel = crearPanelBotones();

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 800));
        revalidate();
        repaint();
    }
    
    private void reiniciarJuego() {
        iniciarJuego();
    }

    private Celda makeButton(int valor, Font font, int row, int col) {
        Celda b = new Celda(valor);
        b.setFont(font);
        b.setPreferredSize(new Dimension(25, 25));
        b.setFocusable(false);
        // Guardar las coordenadas para usar en la lógica
        b.addActionListener(e -> handleButtonClick(b, row, col)); 
        return b;
    }

    private void handleButtonClick(Celda boton, int row, int col) {
        if (!juegoActivo || boton.getEsVisible()) return;

        int valor = boton.getValor();
        boton.setEsVisible(true);

        if (valor == -1) {
            boton.setText("💥");
            boton.setBackground(Color.RED);
            terminarJuego(false);
        } else {
            boton.setEnabled(false);
            celdasReveladas++;
            
            if (valor == 0) {
                boton.setText("");
                boton.setBackground(Color.LIGHT_GRAY);
                // NOTA: Aquí se llama a la propagación si se desea
            } else {
                boton.setText(Integer.toString(valor));
                boton.setForeground(getColorPorNumero(valor));
                boton.setFont(new Font("Arial", Font.BOLD, 18));
            }
            
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

    private Color getColorPorNumero(int n) {
        switch (n) {
            case 1: return Color.BLUE;
            case 2: return new Color(0, 128, 0);
            case 3: return Color.RED;
            case 4: return new Color(0, 0, 128);
            case 5: return new Color(128, 0, 0);
            case 6: return new Color(64, 224, 208);
            case 7: return Color.BLACK;
            case 8: return Color.GRAY;
            default: return Color.BLACK;
        }
    }

    private void mostrarTodosLosBotones(boolean forzarActivacion) {
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                Celda boton = botones[row][col];
                int valor = boton.getValor();
                
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
                    boton.setText(Integer.toString(valor));
                    boton.setForeground(getColorPorNumero(valor));
                    boton.setBackground(Color.LIGHT_GRAY);
                }
                boton.setEnabled(false); 
            }
        }
    }

    private JPanel crearPanelBotones() {
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
        return panel;
    }
}