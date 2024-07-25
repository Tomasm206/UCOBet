package domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Gerente extends Participante{
    public Gerente(String nombre, String documento, String numero, double apuesta) {
        super(nombre, documento, numero, apuesta);
    }
    //elegir hora a jugar
    public void elegirHoraAJugar(LocalDateTime fechaYHoraDelSorteo){
        LocalDateTime ahora = LocalDateTime.now();
        long minutosHastaSorteo = ChronoUnit.MINUTES.between(ahora, fechaYHoraDelSorteo);

        if (minutosHastaSorteo <= 5) {
            System.out.println("Las apuestas están bloqueadas. Faltan menos de 5 minutos para el sorteo.");
        } else {
            System.out.println("Puedes realizar tu apuesta. Tiempo restante: " + minutosHastaSorteo + " minutos.");
        }
    }
    //restringir numero
    //escogerPagoDeGanador
}
