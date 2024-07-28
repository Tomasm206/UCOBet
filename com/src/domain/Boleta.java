package domain;

public class Boleta {
    private String numero;
    private double cantidad;

    public Boleta(String numero, double cantidad) {
        this.numero = numero;
        this.cantidad = cantidad;
    }

    public String getNumero() {
        return numero;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
