public class Calculadora {
    private double operando1;
    private double operando2;

    //Costructor
    public Calculadora() {

    }

    public void colocarOperandos(double a, double b) {this.operando1 = a;
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
        if (operando2 == 0) {
            System.out.println("Division por cero no permitida ");
        }
        return this.operando1 / this.operando2;

    }

}