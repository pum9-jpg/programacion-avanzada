import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TresEnRayaUI extends JFrame implements ActionListener {

    private JButton[][] boardButtons;
    private JLabel statusLabel;
    private JButton resetButton;
    private JuegoTresEnRaya game;

    public TresEnRayaUI() {
        super("Tres En Raya");
        game = new JuegoTresEnRaya();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(350, 450);

        // Fuente para los botones del tablero
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        Font statusFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Etiqueta de estado/turno
        statusLabel = new JLabel(game.getCurrentPlayer() + " Turno");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(statusFont);
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        // 2. Panel del tablero (Grid de 3x3)
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardButtons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = new JButton("");
                boardButtons[i][j].setFont(buttonFont);
                boardButtons[i][j].setFocusable(false);
                boardButtons[i][j].setActionCommand(i + "," + j); // Coordenadas
                boardButtons[i][j].addActionListener(this);
                boardPanel.add(boardButtons[i][j]);
            }
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // 3. Botón de reiniciar
        resetButton = new JButton("Reiniciar Juego");
        resetButton.setFont(statusFont);
        resetButton.addActionListener(e -> resetGame());
        mainPanel.add(resetButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isGameActive()) {
            return;
        }

        // Obtener coordenadas del botón presionado
        String[] parts = e.getActionCommand().split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        // Intentar hacer el movimiento en la lógica del juego
        String result = game.makeMove(row, col);

        if (result != null) {
            // Actualizar el botón y la etiqueta
            boardButtons[row][col].setText(game.getCell(row, col));
            statusLabel.setText(result);
            
            // Si hay ganador o empate, deshabilitar tablero
            if (result.contains("Gana") || result.equals("Empate")) {
                highlightWinner(game.getCurrentPlayer().equals("X") ? "O" : "X");
            }
        }
    }

    private void highlightWinner(String winner) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j].getText().equals(winner)) {
                    boardButtons[i][j].setBackground(Color.GREEN);
                    boardButtons[i][j].setForeground(Color.BLACK);
                } else {
                    boardButtons[i][j].setEnabled(false);
                }
            }
        }
    }

    private void resetGame() {
        game.resetGame();
        statusLabel.setText(game.getCurrentPlayer() + " Turno");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setText("");
                boardButtons[i][j].setEnabled(true);
                boardButtons[i][j].setBackground(UIManager.getColor("Button.background")); // Color por defecto
            }
        }
    }
}