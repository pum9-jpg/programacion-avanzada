public class Calculadora {
    private double operando1;
    private double operando2;

    //Costructor
    public Calculadora() {

    }

    public void colocarOperandos(double a, double b) {
        this.operando1 = a;
        this.operando2 = b;
    }

    public double suma() {
        return this.operando1 + this.operando2;
    }

}