
import javax.swing.*;
import java.awt.*;

public class CalculadoraUI extends JFrame {

    private final JTextField displayTextField;
    private String currentText = "0";
    private double operand1 = 0;
    private String operator = null;
    private boolean resetOnNextDigit = false;
    private Principal calculator; //  cálculos

    private String[][] labelsString = {
            {"C", "±", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"}
        };

    public CalculadoraUI() {
        super("Calculadora");
        calculator = new Principal(); //  inicializa la clase lógica

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        // Fuente y formato
        Font displayFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayTextField = new JTextField("0");
        displayTextField.setEditable(true);
        displayTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayTextField.setFont(displayFont);
        displayTextField.setPreferredSize(new Dimension(260, 60));
        mainPanel.add(displayTextField, BorderLayout.NORTH);

        //  botones trigonométricos y de exponente
        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Fila superior con funciones avanzadas
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton sin = makeButton("sin", buttonFont);
        buttons.add(sin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton cos = makeButton("cos", buttonFont);
        buttons.add(cos, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton tan = makeButton("tan", buttonFont);
        buttons.add(tan, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        JButton xy = makeButton("x^y", buttonFont);
        buttons.add(xy, gbc);

        // Botones numéricos
        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 1;
                JButton b = makeButton(labelsString[row][col], buttonFont);
                buttons.add(b, gbc);
            }
        }

        // Botón "0", ".", "="
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton zero = makeButton("0", buttonFont);
        buttons.add(zero, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        JButton dot = makeButton(".", buttonFont);
        buttons.add(dot, gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        JButton eq = makeButton("=", buttonFont);
        buttons.add(eq, gbc);

        mainPanel.add(buttons, BorderLayout.CENTER);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton makeButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setFocusable(false);
        b.addActionListener(e -> handleButtonClick(text)); // 🔸 MODIFICADO
        return b;
    }

    //Se agregaron funciones sin, cos, tan y exponente
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

            //  se incluyen los operadores avanzados
            case "+": case "-": case "*": case "/": case "%": case "x^y":
                operand1 = Double.parseDouble(currentText);
                operator = text;
                resetOnNextDigit = true;
                break;

            // FUNCIONES MATEMÁTICAS
            case "sin":
                currentText = formatResult(calculator.sin(Double.parseDouble(currentText)));
                break;
            case "cos":
                currentText = formatResult(calculator.cos(Double.parseDouble(currentText)));
                break;
            case "tan":
                currentText = formatResult(calculator.tan(Double.parseDouble(currentText)));
                break;

            default:
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
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }

    public void setDisplayText(String text) {
        displayTextField.setText(text);
    }

    public String getDisplayText() {
        return displayTextField.getText();
    }
}