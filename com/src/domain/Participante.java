package domain;

class Participante {
    String nombre;
    String documento;
    String numero;
    double apuesta;

    public Participante(String nombre, String documento, String numero, double apuesta) {
        this.nombre = nombre;
        this.documento = documento;
        this.numero = numero;
        this.apuesta = apuesta;
    }

    public String getNumero() {
        return numero;
    }
}
