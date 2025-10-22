import java.util.Random;

public class Terreno {
    private int dimension;
    private int[][] celdas;

    public Terreno(int dimension) {
        this.dimension = dimension;
        this.celdas = new int[dimension][dimension];
        generarMinas();
        generarContadores();
    }

    public int getDimension() {
        return dimension;
    }

    private void generarMinas() {
        //Crear minas
        Random random = new Random();
        int CANTIDAD_MINAS = (int)Math.floor(dimension*dimension*0.3) ;
        for (int i = 0; i < CANTIDAD_MINAS; i++) {
            celdas[random.nextInt(dimension)][random.nextInt(dimension)]= -1;
        }
    }

    private void generarContadores(){
        //Crear los contadores
        for (int row = 0; row < celdas.length; row++) {
            for (int col = 0; col < celdas[row].length; col++) {
                int aux = celdas[row][col];
                if (aux != -1) {
                    int contador = 0;

                    // Verificar los 8 vecinos con IFs
                    if (row - 1 >= 0 && celdas[row - 1][col] == -1)
                        contador++;
                    if (row + 1 < celdas.length && celdas[row + 1][col] == -1)
                        contador++;
                    if (col - 1 >= 0 && celdas[row][col - 1] == -1)
                        contador++;
                    if (col + 1 < celdas[row].length && celdas[row][col + 1] == -1)
                        contador++;
                    if (row - 1 >= 0 && col - 1 >= 0 && celdas[row - 1][col - 1] == -1)
                        contador++;
                    if (row - 1 >= 0 && col + 1 < celdas[row].length && celdas[row - 1][col + 1] == -1)
                        contador++;
                    if (row + 1 < celdas.length && col - 1 >= 0 && celdas[row + 1][col - 1] == -1)
                        contador++;
                    if (row + 1 < celdas.length && col + 1 < celdas[row].length && celdas[row + 1][col + 1] == -1)
                        contador++;

                    // Guardar el número de minas vecinas
                    celdas[row][col] = contador;
                } 
            }
        }
    }

    public int getValor(int fila, int columna) {
        return celdas[fila][columna];
    }
}
