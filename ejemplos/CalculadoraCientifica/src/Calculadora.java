import java.util.ArrayList;
import java.util.List;

public class Calculadora {

    private List<String> history;

    public Calculadora() {
        this.history = new ArrayList<>();
    }

    public double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return sum(a, b);
            case "-": return sub(a, b);
            case "*": return multi(a, b);
            case "/": return divi(a, b);
            case "%": return porcentage(a, b);
            case "x^y": return exp(a, b);
            default: return b;
        }
    }
    
    public double calculateUnary(double a, String op) {
        double result = Double.NaN;

        switch (op) {
            case "sin": result = sin(a); break;
            case "cos": result = cos(a); break;
            case "tan": result = tan(a); break;
            case "ln": result = log(a); break;
            case "sqrt": result = sqrt(a); break;
            default: result = a;
        }
        
        String entry = op + "(" + a + ") = " + result;
        history.add(entry);
        return result;
    }

    public double sin(double a) {
        return Math.sin(a);
    }
    
    public double cos(double a) {
        return Math.cos(a);
    }
    
    public double tan(double a) {
        return Math.tan(a);
    }

    public double log(double a) {
        if (a > 0) {
            return Math.log(a);
        }
        return Double.NaN;
    }
    
    public double sqrt(double a) {
        if (a >= 0) {
            return Math.sqrt(a);
        }
        return Double.NaN;
    }
    
    public double sum(double a, double b) {
        double result = a + b;
        String entry = a + " + " + b + " = " + result;
        history.add(entry);
        return result;
    }

    public double sub(double a, double b) {
        double result = a - b;
        String entry = a + " - " + b + " = " + result;
        history.add(entry);
        return result;
    }

    public double multi(double a, double b) {
        double result = a * b;
        String entry = a + " * " + b + " = " + result;
        history.add(entry);
        return result;
    }

    public double divi(double a, double b) {
        double result = b != 0 ? a / b : Double.NaN;
        String entry = a + " / " + b + " = " + result;
        history.add(entry);
        return result;
    }

    public double porcentage(double a, double b) {
        double result = a % b;
        String entry = a + " % " + b + " = " + result;
        history.add(entry);
        return result;
    }

    public double exp(double a, double b) {
        double result = Math.pow(a, b);
        String entry = a + "^" + b + " = " + result;
        history.add(entry);
        return result;
    }
    
    public List<String> getHistory() {
        return history;
    }
}