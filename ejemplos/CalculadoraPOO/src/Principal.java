public class Principal {

    public static void main(String[] args) {
        Calculadora miCalculadora = new Calculadora();

        miCalculadora.colocarOperandos(7, 0);

        System.out.println("Suma: " + miCalculadora.suma());
        System.out.println("Resta: " + miCalculadora.resta());
        System.out.println("Multiplicación: " + miCalculadora.multiplicacion());
        System.out.println("División: " + miCalculadora.division());
    }
}