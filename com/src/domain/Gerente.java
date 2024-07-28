package domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

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
                System.out.print("=== Ingrese la fecha del sorteo ===\n");
                LocalDateTime horaJuego = formatoFechaHora();
                controlTiempoJuego(horaJuego);
                Sorteo.reglas.setHoraDeJuego(horaJuego); // set pone
                return;
            case "2":
                System.out.print("=== Ingrese el numero a restringir ===");
                String numero = scanner.nextLine();
                restringirNumeros(numero);
                break;
            case "3":
                System.out.println("Saliendo del programa..."); //agregar mas casos porque son 4
                return;
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
    public void controlTiempoJuego(LocalDateTime fechaYHoraDelSorteo){
        LocalDateTime ahora = LocalDateTime.now(); // crea una variable del tiempo actual
        long minutosHastaSorteo = ChronoUnit.MINUTES.between(ahora, fechaYHoraDelSorteo); // crear una variable donde compara la fecha que ingreso

        if ((ahora.getHour() < fechaYHoraDelSorteo.getHour()) //Se controla las apuestas despues del sorteo
                && (ahora.getDayOfMonth() == fechaYHoraDelSorteo.getDayOfMonth())){
            System.out.println("Los usuarios pueden realizar apuestas. Tiempo restante: " + minutosHastaSorteo + " minutos.");
        } else if (minutosHastaSorteo <= 5) {
            System.out.println("Las apuestas están bloqueadas. Faltan menos de 5 minutos para el sorteo.");
        } else {
            System.out.println("El Sorteo ya se ha realizado");
        }
    }
    //Formato ingreso de fecha
    public LocalDateTime formatoFechaHora(){
        int dia = leerEntero("Ingrese el día del sorteo (DD)");
        int mes = leerEntero("Ingrese el mes del sorteo (MM)");
        int ano = leerEntero("Ingrese el año del sorteo (AAAA)");
        int hora = leerEntero("Ingrese la hora del sorteo (24H) (HH)");
        int minutos = leerEntero("Ingrese los minutos de la hora del sorteo (MM)");

        return LocalDateTime.of(ano, mes, dia, hora, minutos);
    }
    //Validar ingreso de numeros enteros
    private int leerEntero(String mensaje) {
        while (true) { //Control de ingreso de un numero valido para las fechas
            System.out.print(mensaje + ": ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.next();
            }
        }
    }
    //restringir numero
    public void restringirNumeros(String numeroARestringir){
        while(true){
            if (numeroARestringir.length() == 4 && numeroARestringir.chars().allMatch(Character::isDigit)) {
                System.out.println("El número " + numeroARestringir + " ha sido restringido...");
                Sorteo.reglas.setNumeroRestringido(numeroARestringir);
                break;
            }else{
                System.out.println("El numero ingreado no es valido, intente de nuevo...  ");

            }
        }
    }
}
