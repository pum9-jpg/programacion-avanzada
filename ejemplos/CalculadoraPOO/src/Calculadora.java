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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (this.operando2 == 0) {
         return 0;
        }
        
        return this.operando1 / this.operando2;
=======
=======
>>>>>>> ff775f0af8734aa629f9eb7f0bd4ba2227241a7e
=======
>>>>>>> 23fe419ac19070a5f56051f018313e23c3137391
=======
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
        if (operando2 ==0){
            System.out.println("Division por cero no permitida ");
        }
        return this.operando1 / this.operando2;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> ff775f0af8734aa629f9eb7f0bd4ba2227241a7e
=======
>>>>>>> ff775f0af8734aa629f9eb7f0bd4ba2227241a7e
=======
>>>>>>> 23fe419ac19070a5f56051f018313e23c3137391
=======
>>>>>>> 97e41d8b5dbe4dd5c94d7a3c94e665ce361cad5f
    }

}