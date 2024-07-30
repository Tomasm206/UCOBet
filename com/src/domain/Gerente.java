package domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Gerente{
    private static final Scanner scanner = new Scanner(System.in);
    protected static Boleta numGanador = new Boleta(null,0);

    public void menu(){
        System.out.println("=== MENÚ GERENTE ===");
        System.out.println("1. Elegir hora del juego");
        System.out.println("2. Restringir un número");
        System.out.println("3. Realizar Sorteo");
        System.out.println("4. Ingresar numero ganador");
        System.out.println("5. Volver al menú");
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
                if (Sorteo.reglas.getHoraDeJuego() != null){ //get obtiene
                    restringirNumeros(numero);
                }
                else {
                    System.out.println("El gerente no ha definido una fecha de sorteo...");
                    }
                break;
            case "3":
                System.out.println("=== Realizar el sorteo ===");
                if (Sorteo.reglas.getHoraDeJuego() != null) {
                    controlTiempoJuego(Sorteo.reglas.getHoraDeJuego());
                }else{
                    System.out.println("El gerente no ha definido una fecha de sorteo...");
                }
                return;
            case "4":
                System.out.println("=== Participantes con su respectivo numero ===");
                Sorteo.participantes.forEach(participante -> System.out.println("Nombre: " + participante.nombre + " Numero: " + participante.numero));
                String numeroGanad;
                while (true) {
                    System.out.println("=== Ingreso de numero ganador ===");
                    System.out.print("Porfavor ingrese el numero ganador...");
                    numeroGanad = scanner.nextLine();
                    if ((numeroGanad.length() == 4 && numeroGanad.chars().allMatch(Character::isDigit))){
                        numGanador.setNumero(numeroGanad);
                        System.out.println("EL numero ganador ha sido agregado...");
                        break;
                    } else {
                        System.out.println("Número inválido. Por favor, ingrese un número de 4 cifras...");
                    }
                }
                break;
            case "5":
                System.out.println("Volviendo al menú principal...");
            default:
                System.out.println("Opción inválida. Por favor, ingrese 1, 2, 3, 4 o 5.");
        }
    }
    //elegir hora a jugar
    public void controlTiempoJuego(LocalDateTime fechaYHoraDelSorteo){
        LocalDateTime ahora = LocalDateTime.now(); // crea una variable del tiempo actual
        long minutosHastaSorteo = ChronoUnit.MINUTES.between(ahora, fechaYHoraDelSorteo); // crear una variable donde compara la fecha que ingreso

        if (minutosHastaSorteo <= 0){
            System.out.println("El sorteo ha sido realizado.");
            new Sorteo().realizarSorteo();
        } else {
            System.out.println("El Sorteo no se pudo realizar porque se definió una fecha diferente a la actual...");
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
            System.out.print(mensaje + ": "); //recibe el mensaje introducido en la funcion {formateFechaHora()}
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
