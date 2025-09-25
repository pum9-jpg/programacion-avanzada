public class Principal {

    public static void main(String[] args) {
        Calculadora miCalculadora = new Calculadora();

        miCalculadora.colocarOperandos(4, 8);

        System.out.println("Suma: " + miCalculadora.sumar());
    }
}