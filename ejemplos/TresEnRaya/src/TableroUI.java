import javax.swing.*;
import java.awt.*;

public class TableroUI extends JFrame {

    private boolean turno; // X true y O false

    private String[][] labelsString = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
        };

    // Componentes para mostrar información
    private JLabel labelTurno;
    private JLabel labelJugadorX;
    private JLabel labelJugadorO;
    private JButton[][] botones = new JButton[3][3]; // Para acceder a los botones
    private boolean juegoActivo = true; // Controlar si el juego sigue activo
    
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

        // Panel superior para información de jugadores
        JPanel infoPanel = crearPanelInformacion();
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

 // Crear botones
        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                JButton b = makeButton(labelsString[row][col], buttonFont, row, col);
                botones[row][col] = b; // Guardar referencia del botón
                buttonsPanel.add(b, gbc);
            }
        }

        // Panel de control con botón de reinicio
        JPanel controlPanel = new JPanel();
        JButton reiniciarButton = new JButton("Reiniciar Juego");
        reiniciarButton.addActionListener(e -> reiniciarJuego());
        controlPanel.add(reiniciarButton);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    // Panel superior para información de jugadores
    private JPanel crearPanelInformacion() {
        JPanel infoPanel = new JPanel(new FlowLayout());
        
        // Crear los labels
        labelJugadorX = new JLabel("Jugador X: Humano");
        labelJugadorO = new JLabel("Jugador O: Humano");
        labelTurno = new JLabel("Turno: X");
        
        // Personalizar los labels (opcional)
        labelTurno.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        labelTurno.setForeground(Color.BLUE);
        
        // Añadir al panel
        infoPanel.add(labelJugadorX);
        infoPanel.add(new JLabel(" | "));
        infoPanel.add(labelJugadorO);
        infoPanel.add(new JLabel(" | "));
        infoPanel.add(labelTurno);
        
        return infoPanel;
    }

    private JButton makeButton(String text, Font font, int fila, int columna) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setPreferredSize(new Dimension(80, 80));
        b.setFocusable(false);
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        b.addActionListener(e -> handleButtonClick((JButton) e.getSource(), fila, columna));
        return b;
    }

    private void handleButtonClick(JButton boton, int fila, int columna) {
        // Verificar si el juego está activo y la casilla está vacía
        if (!juegoActivo || !boton.getText().equals(" ")) {
            return; // No hacer nada si el juego terminó o la casilla está ocupada
        }

        // Hacer el movimiento
        if (this.turno) {
            boton.setText("X");
            labelsString[fila][columna] = "X";
        } else {
            boton.setText("O");
            labelsString[fila][columna] = "O";
        }

        // Verificar si hay ganador
        if (verificarGanador()) {
            String ganador = turno ? "X" : "O";
            juegoActivo = false;
            mostrarResultado("¡Jugador " + ganador + " gana!", Color.GREEN);
        } 
        // Verificar si hay empate
        else if (verificarEmpate()) {
            juegoActivo = false;
            mostrarResultado("¡Empate!", Color.ORANGE);
        } 
        // Continuar juego
        else {
            this.turno = !this.turno;
            actualizarTurnoLabel();
        }
    }

    // Método para verificar ganador
    private boolean verificarGanador() {
        String jugador = turno ? "X" : "O";
        
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (labelsString[i][0].equals(jugador) && 
                labelsString[i][1].equals(jugador) && 
                labelsString[i][2].equals(jugador)) {
                resaltarLineaGanadora(i, 0, i, 1, i, 2);
                return true;
            }
        }
        
        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (labelsString[0][i].equals(jugador) && 
                labelsString[1][i].equals(jugador) && 
                labelsString[2][i].equals(jugador)) {
                resaltarLineaGanadora(0, i, 1, i, 2, i);
                return true;
            }
        }
        
        // Verificar diagonal principal
        if (labelsString[0][0].equals(jugador) && 
            labelsString[1][1].equals(jugador) && 
            labelsString[2][2].equals(jugador)) {
            resaltarLineaGanadora(0, 0, 1, 1, 2, 2);
            return true;
        }
        
        // Verificar diagonal secundaria
        if (labelsString[0][2].equals(jugador) && 
            labelsString[1][1].equals(jugador) && 
            labelsString[2][0].equals(jugador)) {
            resaltarLineaGanadora(0, 2, 1, 1, 2, 0);
            return true;
        }
        
        return false;
    }

    // Método para verificar empate
    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (labelsString[i][j].equals(" ")) {
                    return false; // Todavía hay movimientos posibles
                }
            }
        }
        return true; // Todas las casillas llenas y no hay ganador
    }

    // Método para resaltar la línea ganadora
    private void resaltarLineaGanadora(int f1, int c1, int f2, int c2, int f3, int c3) {
        botones[f1][c1].setBackground(Color.GREEN);
        botones[f2][c2].setBackground(Color.GREEN);
        botones[f3][c3].setBackground(Color.GREEN);
    }

    // Método para mostrar resultado
    private void mostrarResultado(String mensaje, Color color) {
        labelTurno.setText(mensaje);
        labelTurno.setForeground(color);
        
        // Mostrar diálogo de resultado
        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        // Reiniciar matriz
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                labelsString[i][j] = " ";
                botones[i][j].setText(" ");
                botones[i][j].setBackground(Color.WHITE); // Resetear color
            }
        }
        
        // Reiniciar variables del juego
        juegoActivo = true;
        turno = true;
        actualizarTurnoLabel();
        labelTurno.setForeground(Color.BLUE);
    }

    // Método para actualizar el label del turno
    private void actualizarTurnoLabel() {
        String jugador = turno ? "X" : "O";
        labelTurno.setText("Turno: " + jugador);
    }

    // Método main para probar el juego
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableroUI().setVisible(true);
        });
    }
}