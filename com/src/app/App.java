package app;

import domain.Gerente;
import domain.Sorteo;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== BIENVENIDO A UCOBET ===");
            System.out.println("¿CÓMO DESEA INGRESAR?");
            System.out.println("1. GERENTE");
            System.out.println("2. USUARIO");
            System.out.println("3. SALIR");
            System.out.print("Ingrese una opción (1/2/3): ");

            String opcion = scanner.nextLine();
            if (opcion.equals("1")) {
                Gerente gerente = new Gerente();
                gerente.menu();
            } else if (opcion.equals("2")) {
                Sorteo sorteo = new Sorteo();
                sorteo.menu();
            } else if (opcion.equals("3")) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("OPCIÓN NO DISPONIBLE");
            }
        }

        scanner.close();
    }
}
