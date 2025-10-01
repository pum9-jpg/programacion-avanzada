public class Principal {

    public static void main(String[] args) {
        Calculadora miCalculadora = new Calculadora();

        miCalculadora.colocarOperandos(4, 8);

        System.out.println("Suma: " + miCalculadora.suma());
        System.out.println("Resta: " + miCalculadora.resta());
        System.out.println("Multiplicacion: " + miCalculadora.multiplicacion());
        System.out.println("Division: " + miCalculadora.dividir());
        }
}

