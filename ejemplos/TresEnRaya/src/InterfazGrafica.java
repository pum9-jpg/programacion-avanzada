//import logica.LogicaJuego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazGrafica extends JFrame implements ActionListener {
    private JButton[][] botones;
    private JLabel labelTurno;
    private LogicaJuego juego;
    
    public InterfazGrafica() {
        // Inicializar el juego
        juego = new LogicaJuego();
        
        // Configurar ventana
        setTitle("3 EN RAYA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        
        // Crear componentes
        crearComponentes();
    }
    
    private void crearComponentes() {
        setLayout(new BorderLayout());
        
        // Label del turno
        labelTurno = new JLabel("Turno: X", SwingConstants.CENTER);
        labelTurno.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelTurno, BorderLayout.NORTH);
        
        // Panel del tablero
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(3, 3));
        
        // Crear botones
        botones = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                botones[i][j].addActionListener(this);
                botones[i][j].setFocusPainted(false);
                panelTablero.add(botones[i][j]);
            }
        }
        add(panelTablero, BorderLayout.CENTER);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Encontrar qué botón fue presionado
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == botones[i][j]) {
                    procesarMovimiento(i, j);
                    return;
                }
            }
        }
    }
    
    private void procesarMovimiento(int fila, int columna) {
        // Intentar hacer el movimiento en la lógica
        if (juego.hacerMovimiento(fila, columna)) {
            // Actualizar la interfaz
            actualizarBoton(fila, columna);
            
            // Verificar si el juego terminó
            if (juego.hayGanador()) {
                mostrarMensajeGanador();
                juego.limpiarTablero();
                actualizarInterfaz();
            } else if (juego.hayEmpate()) {
                mostrarMensajeEmpate();
                juego.limpiarTablero();
                actualizarInterfaz();
            } else {
                // Continuar el juego
                juego.cambiarTurno();
                actualizarLabelTurno();
            }
        }
    }
    
    private void actualizarBoton(int fila, int columna) {
        String simbolo = juego.getSimbolo(fila, columna);
        botones[fila][columna].setText(simbolo);
        
        // Cambiar color según el símbolo
        if (simbolo.equals("X")) {
            botones[fila][columna].setForeground(Color.GREEN);
        } else {
            botones[fila][columna].setForeground(Color.RED);
        }
    }
    
    private void actualizarLabelTurno() {
        labelTurno.setText("Turno: " + juego.getJugadorActual());
    }
    
    private void actualizarInterfaz() {
        // Limpiar todos los botones
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
        actualizarLabelTurno();
    }
    
    
    private void mostrarMensajeGanador() {
        String ganador = juego.getJugadorActual();
        JOptionPane.showMessageDialog(this, "¡El jugador " + ganador + " ha ganado!");
    }
    
    private void mostrarMensajeEmpate() {
        JOptionPane.showMessageDialog(this, "¡Empate!");
    }
    
    public void mostrarVentana() {
        setVisible(true);
    }
}