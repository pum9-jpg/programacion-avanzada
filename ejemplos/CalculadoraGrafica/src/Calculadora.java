public class Calculadora {

    //Costructor
    public Calculadora() {

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

    public double sum(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double multi(double a, double b) {
        return a * b;
    }

    public double divi(double a, double b) {
        return  b != 0 ? a / b : Double.NaN;
    }

    public double porcentage(double a, double b) {
        return a % b;
    }

    public double exp(double a, double b) {
        return Math.pow(a, b);
    }
}