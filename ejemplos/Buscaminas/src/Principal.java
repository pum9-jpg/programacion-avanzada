import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {
    public static void main(String[] args) {
         // Poner la UI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
        // Opcional: setear el look & feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        VentanaUI ui = new VentanaUI();
            ui.setVisible(true);
        });
        // int DIMENSION = 10;
        // int[][] terreno = new int[DIMENSION][DIMENSION];

        // for(int var1 = 0; var1 < DIMENSION; ++var1) {
        //     for(int var2 = 0; var2 < DIMENSION; ++var2) {
        //         terreno[var1][var2] = 0;
        //     }
        // }

        // //Crear minas
        // Random random = new Random();
        // int CANTIDAD_MINAS = (int)Math.floor(DIMENSION*DIMENSION*0.3) ;
        // for (int i = 0; i < CANTIDAD_MINAS; i++) {
        //     terreno[random.nextInt(DIMENSION)][random.nextInt(10)] = -1;
        // }

        //  //Mostrar 
        // for(int var1 = 0; var1 < DIMENSION; ++var1) {
        //     for(int var2 = 0; var2 < DIMENSION; ++var2) {
        //         System.out.print(terreno[var1][var2] + " |");
        //     }
        //     System.out.println();
        // }
    }
}