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
        System.out.println("3. Realizar Sorteo");
        System.out.println("4. Ingresar numero ganador");//Falta metodo completo
        System.out.println("5. Salir");
        System.out.print("Ingrese una opción (1/2/3/4/5): ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("=== Ingrese la fecha del sorteo ===\n");
                LocalDateTime horaJuego = formatoFechaHora();
                Sorteo.reglas.setHoraDeJuego(horaJuego); // set pone
                return;
            case "2":
                System.out.print("=== Ingrese el numero a restringir ===");
                String numero = scanner.nextLine();
                if (Sorteo.reglas.getHoraDeJuego() != null){
                    restringirNumeros(numero);
                }
                else {
                    System.out.println("El gerente no ha definido una fecha de sorteo...");
                    }
                break;
            case "3":
                System.out.println("=== Realizar el sorteo ===");
                controlTiempoJuego(Sorteo.reglas.getHoraDeJuego());
                return;
            case "4":
                System.out.println("=== Ingrese numero ganador ===");
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
        } else if (minutosHastaSorteo <= 5 && minutosHastaSorteo > 0) {
            System.out.println("Las apuestas están bloqueadas. Faltan menos de 5 minutos para el sorteo.");
        } else if (minutosHastaSorteo <= 0){
            System.out.println("La apuesta ha sido realizada o esta en proceso de realizacion");
            new Sorteo().realizarSorteo();
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
    public void restringirNumeros(String restringirNumeros){
        while(true){
            if (restringirNumeros.length() == 4 && restringirNumeros.chars().allMatch(Character::isDigit)) {
                System.out.println("El número " + restringirNumeros + " ha sido restringido...");
                Sorteo.reglas.setNumeroRestringido(restringirNumeros);
                break;
            }else{
                System.out.println("El numero ingreado no es valido, intente de nuevo...  ");

            }
        }
    }
}
