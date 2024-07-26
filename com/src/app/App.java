package app;

import domain.Gerente;
import domain.Sorteo;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A UCOBET ===");
        System.out.println("¿COMO DESEA INGRESAR?");
        System.out.println("1. GERENTE");
        System.out.println("2. USUARIO");

        String opcion = scanner.nextLine();
        if (opcion.equals("1")){
            Gerente gerente = new Gerente();
            gerente.menu();
        } else if (opcion.equals("2")) {
            Sorteo sorteo = new Sorteo();
            sorteo.menu();
        }else {
            System.out.println("OPCION NO DISPONIBLE");
        }
    }
}
