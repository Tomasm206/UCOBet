package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Sorteo {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final List<Participante> participantes = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Ingresar datos de persona");
            System.out.println("2. Realizar sorteo");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción (1/2/3): ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ingresarDatos();
                    break;
                case "2":
                    realizarSorteo();
                    break;
                case "3":
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese 1, 2 o 3.");
            }
        }
    }

    private static void ingresarDatos() {
        System.out.println("=== Menú para ingresar datos de persona ===");
        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el número de documento: ");
        String documento = scanner.nextLine();

        String numero;
        while (true) {
            System.out.print("Ingrese un número de 4 cifras para el sorteo: ");
            numero = scanner.nextLine();
            if (numero.length() == 4 && numero.chars().allMatch(Character::isDigit)) {
                break;
            } else {
                System.out.println("Número inválido. Por favor, ingrese un número de 4 cifras.");
            }
        }

        double apuesta;
        while (true) {
            try {
                System.out.print("Ingrese la cantidad de dinero que desea apostar: ");
                apuesta = Double.parseDouble(scanner.nextLine());
                if (apuesta > 0) {
                    break;
                } else {
                    System.out.println("La apuesta debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Por favor, ingrese un número válido.");
            }
        }

        int numCifras;
        while (true) {
            System.out.println("¿Cuántas cifras del número desea coger para el sorteo?");
            System.out.println("1. 1 cifra");
            System.out.println("2. 2 cifras");
            System.out.println("3. 3 cifras");
            System.out.println("4. 4 cifras");
            System.out.print("Ingrese una opción (1/2/3/4): ");
            String eleccion = scanner.nextLine();
            if (eleccion.matches("[1-4]")) {
                numCifras = Integer.parseInt(eleccion);
                break;
            } else {
                System.out.println("Opción inválida. Por favor, ingrese 1, 2, 3 o 4.");
            }
        }

        String numeroCogido = numero.substring(numero.length() - numCifras);

        participantes.add(new Participante(nombre, documento, numeroCogido, apuesta));

        System.out.println("\nDatos ingresados:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Número de documento: " + documento);
        System.out.println("Número para el sorteo: " + numeroCogido);
        System.out.println("Cantidad apostada: $" + apuesta);
        System.out.println("\n¡Datos almacenados correctamente!\n");
    }

    private static void realizarSorteo() {
        if (participantes.isEmpty()) {
            System.out.println("No hay participantes para realizar el sorteo.");
            return;
        }

        Participante ganador = participantes.get(random.nextInt(participantes.size()));
        System.out.println("¡El ganador del sorteo es: " + ganador.nombre + " con el número " + ganador.numero + "!");
        System.out.println("Ha apostado: $" + ganador.apuesta);
    }
}

