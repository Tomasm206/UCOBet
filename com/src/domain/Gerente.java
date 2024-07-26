package domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import domain.Sorteo;

public class Gerente{
    private static final Scanner scanner = new Scanner(System.in);


    public void menu(){
        System.out.println("=== MENÚ GERENTE ===");
        System.out.println("1. Elegir hora del juego");
        System.out.println("2. Restringir un número");
        System.out.println("3. Escoger pago ganador");
        System.out.println("4. Ingresar numero ganador");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opción (1/2/3/4/5): ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese la fecha del sorteo  ");
                //elegirHoraAJugar(); consultar bien el formato a poner dentro de las "()"
                break;
            case "2":
                String numero = scanner.nextLine();
                restringirNumeros(numero);
                break;
            case "3":
                System.out.println("Saliendo del programa..."); //agregar mas casos porque son 4
                break;
            case "4":
                System.out.println("Ingresar numero ganador...");
                break;
            case "5":
                System.out.println("Salir del programa");
            default:
                System.out.println("Opción inválida. Por favor, ingrese 1, 2, 3 o 4.");
        }
    }
    //elegir hora a jugar
    public void elegirHoraAJugar(LocalDateTime fechaYHoraDelSorteo){
        LocalDateTime ahora = LocalDateTime.now();
        long minutosHastaSorteo = ChronoUnit.MINUTES.between(ahora, fechaYHoraDelSorteo);

        if (minutosHastaSorteo <= 5) {
            System.out.println("Las apuestas están bloqueadas. Faltan menos de 5 minutos para el sorteo.");
        } else {
            System.out.println("Puedes realizar tu apuesta. Tiempo restante: " + minutosHastaSorteo + " minutos.");
            //Llamar funcion sorteo
        }
    }
    //restringir numero
    public void restringirNumeros(String numeroARestringir){
        Sorteo sorteo = new Sorteo();
        sorteo.realizarSorteo(numeroARestringir);

    }
    //escogerPagoDeGanador

}
