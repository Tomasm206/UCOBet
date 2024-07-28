package domain;

import java.time.LocalDateTime;

public class Reglas {
    private LocalDateTime horaDeJuego;//Estamos creando una variable con formato de fecha
    private String numeroRestringido;

    public Reglas(LocalDateTime horaDeJuego, String numeroRestringido) { // Contructor
        this.horaDeJuego = horaDeJuego;
        this.numeroRestringido = numeroRestringido;
    }

    public LocalDateTime getHoraDeJuego() { // obtener los resultados
        return horaDeJuego;
    }

    public String getNumeroRestringido() {
        return numeroRestringido;
    }

    public void setHoraDeJuego(LocalDateTime horaDeJuego) { // poner los resultados
        this.horaDeJuego = horaDeJuego;
    }

    public void setNumeroRestringido(String numeroRestringido) {
        this.numeroRestringido = numeroRestringido;
    }
}
