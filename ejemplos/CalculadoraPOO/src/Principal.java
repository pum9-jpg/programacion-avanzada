import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculadora miCalculadora = new Calculadora();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== MENÚ CALCULADORA =====");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = sc.nextInt();

            if (opcion == 5) {
                System.out.println("Gracias por usar la calculadora. ¡Adiós!");
                break;
            }

            System.out.print("Ingresa el primer número: ");
            double a = sc.nextDouble();
            System.out.print("Ingresa el segundo número: ");
            double b = sc.nextDouble();
            miCalculadora.colocarOperandos(a, b);

            switch (opcion) {
                case 1:
                    System.out.println("Resultado: " + miCalculadora.suma());
                    break;
                case 2:
                    System.out.println("Resultado: " + miCalculadora.resta());
                    break;
                case 3:
                    System.out.println("Resultado: " + miCalculadora.multiplicacion());
                    break;
                case 4:
                    System.out.println("Resultado: " + miCalculadora.division());
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.print("\n¿Quieres realizar otra operación? (s/n): ");
            char respuesta = sc.next().toLowerCase().charAt(0);
            if (respuesta != 's') {
                continuar = false;
                System.out.println("Gracias por usar la calculadora. ¡Adiós!");
            }
        }

        sc.close();
    }
}