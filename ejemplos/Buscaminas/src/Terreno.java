import java.util.Random;

public class Terreno {
    private int dimension;
    private int[][] celdas;
    private int celdasSeguras;

    public Terreno(int dimension) {
        this.dimension = dimension;
        this.celdas = new int[dimension][dimension];
        generarMinas();
        generarContadores();
    }

    public int getDimension() {
        return dimension;
    }

    public int getCeldasSeguras() {
        return celdasSeguras;
    }

    private void generarMinas() {
        Random random = new Random();
        int CANTIDAD_MINAS = (int)Math.floor(dimension*dimension*0.3);
        
        celdasSeguras = dimension * dimension - CANTIDAD_MINAS;
        int minasColocadas = 0;
        
        while (minasColocadas < CANTIDAD_MINAS) {
            int r = random.nextInt(dimension);
            int c = random.nextInt(dimension);
            
            if (celdas[r][c] != -1) {
                celdas[r][c] = -1; // -1 = Mina
                minasColocadas++;
            }
        }
    }

    private void generarContadores(){
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (celdas[row][col] != -1) {
                    // Usar la función limpia para contar
                    celdas[row][col] = countMinesAround(row, col);
                }
            }
        }
    }

    // Método refactorizado para el conteo de minas
    private int countMinesAround(int r, int c) {
        int contador = 0;
        // Bucle 3x3 que revisa las celdas adyacentes (dr, dc van de -1 a 1)
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // No revisar la celda actual

                int nr = r + dr; // Fila Vecina
                int nc = c + dc; // Columna Vecina

                // 1. Verificar límites
                if (nr >= 0 && nr < dimension && nc >= 0 && nc < dimension) {
                    // 2. Verificar si es una mina
                    if (celdas[nr][nc] == -1) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public int getValor(int fila, int columna) {
        return celdas[fila][columna];
    }
}
