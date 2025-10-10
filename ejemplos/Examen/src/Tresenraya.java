public class JuegoTresEnRaya {

    private String[][] board;
    private String currentPlayer;
    private boolean gameActive;

    public JuegoTresEnRaya() {
        board = new String[3][3];
        currentPlayer = "X";
        gameActive = true;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public String makeMove(int row, int col) {
        if (!gameActive || !board[row][col].equals("")) {
            return null; // Movimiento inválido
        }

        board[row][col] = currentPlayer;
        String winner = checkWinner();

        if (winner != null) {
            gameActive = false;
            return winner.equals("Draw") ? "Empate" : winner + " Gana";
        }

        switchPlayer();
        return currentPlayer + " Turno";
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private String checkWinner() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(currentPlayer) && board[i][1].equals(currentPlayer) && board[i][2].equals(currentPlayer)) {
                return currentPlayer; // Ganador por fila
            }
            if (board[0][i].equals(currentPlayer) && board[1][i].equals(currentPlayer) && board[2][i].equals(currentPlayer)) {
                return currentPlayer; // Ganador por columna
            }
        }

        // Verificar diagonales
        if (board[0][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][2].equals(currentPlayer)) {
            return currentPlayer; // Diagonal principal
        }
        if (board[0][2].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][0].equals(currentPlayer)) {
            return currentPlayer; // Diagonal secundaria
        }

        // Verificar empate
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    isBoardFull = false;
                    break;
                }
            }
        }
        if (isBoardFull) {
            return "Draw";
        }

        return null; // No hay ganador ni empate aún
    }

    public void resetGame() {
        initializeBoard();
        currentPlayer = "X";
        gameActive = true;
    }

    public String getCell(int row, int col) {
        return board[row][col];
    }
}