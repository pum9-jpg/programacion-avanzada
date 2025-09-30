import javax.swing.*;
import java.awt.*;

public class CalculadoraUI extends JFrame {

    private final JTextField display;
    
    public CalculadoraUI() {
        super("Calculadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        // Fuente y dimensiones recomendadas
        Font displayFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel principal
        JPanel main = new JPanel(new BorderLayout(8, 8));
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Display (pantalla) - no editable por teclado, alineado a la derecha
        display = new JTextField("0");
        display.setEditable(true);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(displayFont);
        display.setPreferredSize(new Dimension(260, 60));

        main.add(display, BorderLayout.NORTH);




        setContentPane(main);
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }
    
}