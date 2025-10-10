public class LogicaJuego {
    private String[][] tablero;
    private boolean turnoX; // true = X, false = O
    
    public LogicaJuego() {
        tablero = new String[3][3];
        turnoX = true;
        limpiarTablero();
    }
    
    // Limpiar el tablero (iniciar vacío)
    public void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
            }
        }
        turnoX = true;
    }
    
    // Realizar un movimiento
    public boolean hacerMovimiento(int fila, int columna) {
        // Verificar si la casilla está vacía
        if (tablero[fila][columna].equals("")) {
            // Colocar X o O según el turno
            tablero[fila][columna] = turnoX ? "X" : "O";
            return true;
        }
        return false;
    }
    
    // Cambiar turno
    public void cambiarTurno() {
        turnoX = !turnoX;
    }
    
    // Verificar si hay ganador
    public boolean hayGanador() {
        String simbolo = turnoX ? "X" : "O";
        
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(simbolo) && 
                tablero[i][1].equals(simbolo) && 
                tablero[i][2].equals(simbolo)) {
                return true;
            }
        }
        
        // Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j].equals(simbolo) && 
                tablero[1][j].equals(simbolo) && 
                tablero[2][j].equals(simbolo)) {
                return true;
            }
        }
        
        // Verificar diagonales
        if (tablero[0][0].equals(simbolo) && 
            tablero[1][1].equals(simbolo) && 
            tablero[2][2].equals(simbolo)) {
            return true;
        }
        
        if (tablero[0][2].equals(simbolo) && 
            tablero[1][1].equals(simbolo) && 
            tablero[2][0].equals(simbolo)) {
            return true;
        }
        
        return false;
    }
    
    // Verificar empate
    public boolean hayEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].equals("")) {
                    return false; // Hay casilla vacía
                }
            }
        }
        return true; // Tablero lleno
    }
    
    
    public boolean esTurnoX() {
        return turnoX;
    }
    
    public String getSimbolo(int fila, int columna) {
        return tablero[fila][columna];
    }
    
    public String getJugadorActual() {
        return turnoX ? "X" : "O";
    }
}