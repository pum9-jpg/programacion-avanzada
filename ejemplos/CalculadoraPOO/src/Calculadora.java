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

    public double resta() {
        return this.operando1 - this.operando2;
    }

    public double multiplicacion() {
        return this.operando1 * this.operando2;
    }

    public double division() {
<<<<<<< HEAD
        if (this.operando2 == 0) {
         return 0;
        }
        
        return this.operando1 / this.operando2;
=======
        if (operando2 ==0){
            System.out.println("Division por cero no permitida ");
        }
        return this.operando1 / this.operando2;

>>>>>>> ff775f0af8734aa629f9eb7f0bd4ba2227241a7e
    }

}