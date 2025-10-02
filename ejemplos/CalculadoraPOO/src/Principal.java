import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculadora op = new Calculadora();
        int opcion = 0;
        double num1, num2, resultado;

        do {
            System.out.println("---CALCULADORA---");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            if (opcion >= 1 && opcion <= 4) {
                System.out.print("Ingresa el primer número: ");
                num1 = sc.nextDouble();
                System.out.print("Ingresa el segundo número: ");
                num2 = sc.nextDouble();

                switch (opcion) {
                    case 1:
                        resultado = op.sumar(num1, num2);
                        System.out.println("La suma es : " + resultado);
                        break;
                    case 2:
                        resultado = op.restar(num1, num2);
                        System.out.println("La resta es : " + resultado);
                        break;
                    case 3:
                        resultado = op.multiplicar(num1, num2);
                        System.out.println("La Multiplicación es : " + resultado);
                        break;
                    case 4:
                        resultado = op.dividir(num1, num2);
                        System.out.println("Resultado: " + resultado);
                        break;
                }
            } else if (opcion != 5) {
                System.out.println("Opción no válida.");
            }
            System.out.println();

        } while (opcion != 5);

        System.out.println("usted salio del menu calculadora");
        sc.close();
    }
}
