package domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sorteo {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    protected static final List<Participante> participantes = new ArrayList<>();
    protected static Reglas reglas = new Reglas(null, null);
    protected static Boleta boleta = new Boleta(null, 0);

    public void menu() {
        while (true) {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Ingresar datos de persona");
            System.out.println("2. Consultar fecha y numero restringido");
            System.out.println("3. Volver al menú");
            System.out.print("Ingrese una opción (1/2/3): ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    if (reglas.getHoraDeJuego() != null){
                        condicionesIngresoDatosParaSorteo(reglas.getHoraDeJuego());
                    } else {
                        System.out.println("No se ha definido una fecha para el sorteo...");
                    }
                    break;
                case "2":
                //Crear funcion para revisar numeros restringido y la fecha
                    break;
                case "3":
                    System.out.println("Volviendo al menú principal...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese 1, 2 o 3.");
            }
        }
    }

    public void condicionesIngresoDatosParaSorteo(LocalDateTime fechaYHoraDelSorteo){ //funcion creada para restringir el ingreso antes de 5 min y despues del sorteo
        LocalDateTime ahora = LocalDateTime.now(); // crea una variable del tiempo actual
        long minutosHastaSorteo = ChronoUnit.MINUTES.between(ahora, fechaYHoraDelSorteo); // crear una variable donde compara la fecha que ingreso

        if ((ahora.getHour() < fechaYHoraDelSorteo.getHour()) //Se controla las apuestas despues del sorteo
                && (ahora.getDayOfMonth() == fechaYHoraDelSorteo.getDayOfMonth()) //Rectifica que el sorteo sea hoy para evitar mostrar minutos erroneos
                && (minutosHastaSorteo > 5)){ //Condicion de los 5 minutos
            System.out.println("Aún puedes realizar tu apuestas. Tiempo restante: " + minutosHastaSorteo + " minutos.");
            System.out.println("=== Ingresa tus datos y tu apuesta ===");
            ingresarDatos();
        } else if ((minutosHastaSorteo <= 5 && minutosHastaSorteo > 0) && (ahora.getDayOfMonth() == fechaYHoraDelSorteo.getDayOfMonth())) {
            System.out.println("Las apuestas están bloqueadas. Faltan menos de 5 minutos para el sorteo.");
        } else if (minutosHastaSorteo <= 0 && (ahora.getDayOfMonth() == fechaYHoraDelSorteo.getDayOfMonth())){
            System.out.println("El sorteo ya se ha realizado, consulte si estan disponibles los resultados...");
        } else {
            System.out.println("La fecha es del sorteo es diferente a la actual");
        }
    }

    private static void ingresarDatos() {
        while (true) {
            System.out.println("=== Menú para ingresar datos de persona ===");
            System.out.print("Ingrese el nombre de la persona: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el número de documento: ");
            String documento = scanner.nextLine();

            String numero;
            while (true) {
                String numeroRestringido = String.valueOf(reglas.getNumeroRestringido());
                System.out.print("Ingrese un número de 4 cifras para el sorteo: ");
                numero = scanner.nextLine();

                if ((numero.length() == 4 && numero.chars().allMatch(Character::isDigit)) && (!numero.equals(numeroRestringido))) {
                    System.out.println("El número " + numero + "\nHa sido ingresado correctamente...");
                    boleta.setNumero(numero);
                    break;
                } else if (numero.equals(numeroRestringido)) {
                    System.out.println("El gerente ha restringido este numero. Por favor, elija otro número...");
                } else {
                    System.out.println("Número inválido. Por favor, ingrese un número de 4 cifras...");
                }
            }

            double apuesta;
            while (true) {
                try {
                    System.out.print("Ingrese la cantidad de dinero que desea apostar: ");
                    apuesta = Double.parseDouble(scanner.nextLine());
                    if (apuesta > 0) {
                        System.out.println("La apuesta " + apuesta + "\nHa sido ingresada correctamente...");
                        boleta.setCantidad(apuesta);
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

            // Multiplicar la apuesta según el número de cifras indicado
            switch (numCifras) {
                case 1:
                    apuesta *= 10;
                    break;
                case 2:
                    apuesta *= 15;
                    break;
                case 3:
                    apuesta *= 50;
                    break;
                case 4:
                    apuesta *= 100;
                    break;
            }

            participantes.add(new Participante(nombre, documento, numeroCogido, apuesta));

            System.out.println("\nDatos ingresados:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Número de documento: " + documento);
            System.out.println("Número para el sorteo: " + numeroCogido);
            System.out.println("Cantidad apostada: $" + apuesta);
            System.out.println("\n¡Datos almacenados correctamente!\n");

            System.out.print("¿Desea ingresar otro participante? (SI/NO): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    public void realizarSorteo() {
        List<Participante> participantesGanadores = participantes.stream()
                .filter(person -> person.getNumero().equals(Gerente.numGanador.getNumero())).collect(Collectors.toList());

        if (participantes.isEmpty()) {
            System.out.println("No hay participantes para realizar el sorteo.");
        } else if (!participantesGanadores.isEmpty()) {
            System.out.println("=== Los ganadores son ===");
            participantesGanadores.forEach(gana -> System.out.println("Nombre: " + gana.nombre + " ganó con el número: " + gana.numero));
        } else {
            Participante ganador = participantes.get(random.nextInt(participantes.size()));
            System.out.println("¡El ganador del sorteo es: " + ganador.nombre + " con el número " + ganador.numero + "!");
            System.out.println("Ha apostado: $" + ganador.apuesta);
        }
    }
}
