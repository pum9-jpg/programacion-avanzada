//  Calculadora.java

import javax.swing.SwingUtilities;

public class Principal  {

    // Incluye operador x^y
    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : Double.NaN;
            case "%": return a % b;
            case "x^y": return Math.pow(a, b); 
            default: return 0;
        }
    }

      // FUNCIONES TRIGONOMÉTRICAS (¡Ya corregido en este archivo!)
    public double sin(double x) {
        return Math.sin(Math.toRadians(x)); // convierte grados a radianes
    }

    public double cos(double x) {
        return Math.cos(Math.toRadians(x));
    }

    public double tan(double x) {
        return Math.tan(Math.toRadians(x));
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            CalculadoraUI frame = new CalculadoraUI();
            frame.setVisible(true);
            });
    }
}