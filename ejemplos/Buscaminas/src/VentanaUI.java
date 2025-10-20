import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VentanaUI extends JFrame {
    
    private final int DIMENSION = 10;
    private int[][] terreno;
    
    private boolean juegoActivo;
    private int celdasReveladas;
    private final int CELDAS_SEGURAS;
    
    private JPanel buttonsPanel;
    private JButton resetButton;

    public VentanaUI() {
        super("Buscaminas");
        
        int CANTIDAD_MINAS = (int)Math.floor(DIMENSION*DIMENSION*0.3);
        CELDAS_SEGURAS = DIMENSION * DIMENSION - CANTIDAD_MINAS;
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        iniciarJuego();
        
        pack();
        setLocationRelativeTo(null); 
    }
    
    private void iniciarJuego() {
        this.terreno = new int[DIMENSION][DIMENSION];
        this.juegoActivo = true;
        this.celdasReveladas = 0;
        
        initializeTerreno(); 
        buildUI();
    }
    
    private void buildUI() {
        if (getContentPane() != null) {
            getContentPane().removeAll();
        }
        
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resetButton = new JButton("Reiniciar Juego");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> reiniciarJuego()); 
        mainPanel.add(resetButton, BorderLayout.NORTH);

        buttonsPanel = new JPanel(new GridLayout(DIMENSION, DIMENSION, 2, 2)); 
        
        for (int row = 0; row < DIMENSION; row++) {
            for (int col = 0; col < DIMENSION; col++) {
                
                int valorCelda = terreno[row][col];
                Celda b = new Celda(valorCelda); 
                
                JButton button = makeButton(b, buttonFont);
                buttonsPanel.add(button);
            }
        }

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 800));
        revalidate();
        repaint();
    }
    
    private void reiniciarJuego() {
        iniciarJuego(); 
    }

    private void initializeTerreno() {
        for (int r = 0; r < DIMENSION; r++) {
            for (int c = 0; c < DIMENSION; c++) {
                terreno[r][c] = 0;
            }
        }
        placeMines();
        calculateNeighborCounts();
    }
    
    private void placeMines() {
        Random random = new Random();
        int CANTIDAD_MINAS = (int)Math.floor(DIMENSION*DIMENSION*0.3) ;
        int minasColocadas = 0;
        
        while (minasColocadas < CANTIDAD_MINAS) {
             int r = random.nextInt(DIMENSION);
             int c = random.nextInt(DIMENSION);
             
             if (terreno[r][c] != -1) {
                 terreno[r][c] = -1;
                 minasColocadas++;
             }
        }
    }

    private void calculateNeighborCounts() {
        for (int r = 0; r < DIMENSION; r++) {
            for (int c = 0; c < DIMENSION; c++) {
                if (terreno[r][c] != -1) {
                    terreno[r][c] = countMinesAround(r, c);
                }
            }
        }
    }
    
    private int countMinesAround(int r, int c) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                
                int nr = r + dr;
                int nc = c + dc;

                if (nr >= 0 && nr < DIMENSION && nc >= 0 && nc < DIMENSION) {
                    if (terreno[nr][nc] == -1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private JButton makeButton(Celda celda, Font font) {
        celda.setFont(font);
        celda.setPreferredSize(new Dimension(25, 25));
        celda.setFocusable(false);
        celda.addActionListener(e -> handleButtonClick((Celda) e.getSource()));
        return celda;
    }

    private void handleButtonClick(Celda boton) {
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
            } else {
                boton.setText(Integer.toString(valor));
                boton.setForeground(getColorPorNumero(valor));
                boton.setFont(new Font("Arial", Font.BOLD, 18));
            }
            
            if (celdasReveladas == CELDAS_SEGURAS) {
                terminarJuego(true);
            }
        }
    }
    
    private void terminarJuego(boolean haGanado) {
        juegoActivo = false;
        
        for (Component comp : buttonsPanel.getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).setEnabled(false);
            }
        }
        
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
}