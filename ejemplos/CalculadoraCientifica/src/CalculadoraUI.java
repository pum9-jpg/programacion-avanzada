import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculadoraUI extends JFrame {

    private final JTextField displayTextField;
    private final JTextArea historyTextArea;
    
    private String currentText = "0";
    private double operand1 = 0;
    private String operator = null;
    private boolean resetOnNextDigit = false;
    private Calculadora calculator;

    private String[][] labelsString = {
        {"sin", "cos", "tan", "ln", "sqrt"},
        {"C", "±", "%", "/", "Historial"},
        {"7", "8", "9", "*"},
        {"4", "5", "6", "-"},
        {"1", "2", "3", "+"}
    };
    
    public CalculadoraUI() {
        super("Calculadora Científica");
        calculator = new Calculadora();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(600, 450);

        Font displayFont = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayTextField = new JTextField("0");
        displayTextField.setEditable(false);
        displayTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayTextField.setFont(displayFont);
        displayTextField.setPreferredSize(new Dimension(300, 60));
        mainPanel.add(displayTextField, BorderLayout.NORTH);

        historyTextArea = new JTextArea("Historial de Operaciones\n", 8, 15);
        historyTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Historial"));
        scrollPane.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(scrollPane, BorderLayout.EAST);

        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int row = 0; row < labelsString.length; row++) {
            for (int col = 0; col < labelsString[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.gridwidth = 1;

                JButton b = makeButton(labelsString[row][col], buttonFont);
                buttons.add(b, gbc);
            }
        }

        gbc.gridx = 0;
        gbc.gridy = labelsString.length;
        gbc.gridwidth = 2;
        JButton zero = makeButton("0", buttonFont);
        buttons.add(zero, gbc);

        gbc.gridx = 2;
        gbc.gridy = labelsString.length;
        gbc.gridwidth = 1;
        JButton dot = makeButton(".", buttonFont);
        buttons.add(dot, gbc);

        gbc.gridx = 3;
        gbc.gridy = labelsString.length;
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
                    resetOnNextDigit = true;
                }
                break;
            case "Historial":
                showHistory();
                break;
            
            case "sin": case "cos": case "tan": case "ln": case "sqrt":
                double val = Double.parseDouble(currentText);
                double resultUnary = calculator.calculateUnary(val, text);
                currentText = formatResult(resultUnary);
                resetOnNextDigit = true;
                break;

            case "+": case "-": case "*": case "/": case "%": case "x^y":
                if (operator != null) {
                    double operand2 = Double.parseDouble(currentText);
                    operand1 = calculator.calculate(operand1, operand2, operator);
                    currentText = formatResult(operand1);
                } else {
                    operand1 = Double.parseDouble(currentText);
                }
                operator = text;
                resetOnNextDigit = true; 
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
    
    private void showHistory() {
        historyTextArea.setText("Historial de Operaciones:\n");
        List<String> history = calculator.getHistory();
        for (int i = history.size() - 1; i >= 0; i--) {
            historyTextArea.append(history.get(i) + "\n");
        }
    }

    private String formatResult(double value) {
        if (Double.isNaN(value)) {
             return "Error";
        }
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}