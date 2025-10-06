import javax.swing.*;
import java.awt.*;

public class CalculadoraUI extends JFrame {

    private final JTextField displayTextField;
    private String currentText = "0"; // estado de pantalla
    private double operand1 = 0;
    private String operator = null;
    private boolean resetOnNextDigit = false;
    private Calculadora calculator;
    
    public CalculadoraUI() {
        super("Calculadora");
        calculator = new Calculadora();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        // Fuente y dimensiones recomendadas
        Font displayFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Display (pantalla) - no editable por teclado, alineado a la derecha
        displayTextField = new JTextField("0");
        displayTextField.setEditable(true);
        displayTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayTextField.setFont(displayFont);
        displayTextField.setPreferredSize(new Dimension(260, 60));

        mainPanel.add(displayTextField, BorderLayout.NORTH);

        // Panel de botones con GridBag para permitir que el botón 0 ocupe más espacio
        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        String[][] labelsString = {
            {"C", "±", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"}
        };

        // Filas 0..3 (4 columnas)
        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                JButton b = makeButton(labelsString[row][col], buttonFont);
                // Placeholder: aquí podrías añadir ActionListeners para cada botón
                buttons.add(b, gbc);
            }
        }

        // Última fila: 0 (dos columnas), ., =
        // Botón 0 que ocupa dos columnas
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton zero = makeButton("0", buttonFont);
        buttons.add(zero, gbc);

        // Botón '.'
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JButton dot = makeButton(".", buttonFont);
        buttons.add(dot, gbc);

        // Botón '='
        gbc.gridx = 3;
        gbc.gridy = 4;
        JButton eq = makeButton("=", buttonFont);
        buttons.add(eq, gbc);

        mainPanel.add(buttons, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    private JButton makeButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setFocusable(false);
        // Aquí se deja el ActionListener vacío como marcador. Ejemplo:
        b.addActionListener(e -> handleButtonClick(text));

        return b;
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                currentText = "0";
                operator = null;
                operand1 = 0;

                break;
            case "±":
                if (!currentText.equals("0")) {
                    if (currentText.startsWith("-")) {
                        currentText = currentText.substring(1);
                    } else {
                        currentText = "-" + currentText;
                    }
                }

                break;
            case ".":
                if (resetOnNextDigit) {
                    currentText = "0";
                    resetOnNextDigit = false;
                }
                if (!currentText.contains(".")) {
                    currentText += ".";
                }

                break;
            case "=":
                 if (operator != null) {
                    double operand2 = Double.parseDouble(currentText);
                    double result = calculator.calculate(operand1, operand2, operator);
                    currentText = formatResult(result);
                    operator = null;
                }
                
                break;
            case "+": case "-": case "*": case "/": case "%":
                operand1 = Double.parseDouble(currentText);
                operator = text;
                resetOnNextDigit = true; // la próxima cifra reinicia
                break;
            default: // números
                if (resetOnNextDigit) {
                    currentText = text;
                    resetOnNextDigit = false;
                } else if (currentText.equals("0")) {
                    currentText = text;
                } else {
                    currentText += text;
                }
                break;
        }
        displayTextField.setText(currentText);
    }

    private String formatResult(double value) {
        // Elimina .0 si es entero
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }

    // Método de utilidad para actualizar la pantalla desde la lógica (cuando se implemente)
    public void setDisplayText(String text) {
        displayTextField.setText(text);
    }

    public String getDisplayText() {
        return displayTextField.getText();
    }

}