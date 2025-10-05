public class Calculadora {
    private double operando1;
    private double operando2;

    public Calculadora() {}

    public void colocarOperandos(double a, double b) {
        this.operando1 = a;
        this.operando2 = b;
    }

    public double suma() {
        return this.operando1 + this.operando2;
    }

    public double resta() {
        return this.operando1 - this.operando2;
    }

    public double multiplicacion() {
        return this.operando1 * this.operando2;
    }

    public double division() {
        if (this.operando2 != 0) {
            return this.operando1 / this.operando2;
        } else {
            System.out.println("Error: No se puede dividir entre 0");
            return 0;
        }
    }
}
