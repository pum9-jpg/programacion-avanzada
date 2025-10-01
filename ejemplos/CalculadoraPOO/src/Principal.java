
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Calculadora miCalculadora = new Calculadora();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        double a, b;
        do {
            System.out.println("--- CALCULADORA BÁSICA ---");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            System.out.print("Ingrese el primer número: ");
            a = scanner.nextDouble();
            System.out.print("Ingrese el segundo número: ");
            b = scanner.nextDouble();

           /* switch (opcion) {
                case 1:
                    System.out.println("El resultado de la suma es : " + miCalculadora.suma());
                    break;
                case 2:
                    System.out.println("El resultado de la resta es : " + miCalculadora.resta());
                    break;
                case 3:
                    System.out.println("Resultado de la multiplicación es : " + miCalculadora.multiplicacion());
                    break;
                case 4:
                    System.out.println("Resultado de la division es : " + miCalculadora.division());
                    break;
            }

        } while (opcion != 5);

    }
}