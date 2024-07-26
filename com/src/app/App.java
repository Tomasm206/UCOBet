package app;

import domain.Gerente;
import domain.Sorteo;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Sorteo sorteo = new Sorteo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A UCOBET ===");
        System.out.println("¿COMO DESEA INGRESAR?");
        System.out.println("1. GERENTE");
        System.out.println("3. USUARIO");

        String opcion = scanner.nextLine();
        
    }
}
