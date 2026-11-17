package mx.uady.filey.modelo;

public class Stand {
    private int id;
    private double metrosCuadrados;
    private double precio;
    private EstadoStand estado;

    public Stand(int id, double metrosCuadrados, double precio, EstadoStand estado) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId() { return id; }
    public double getMetrosCuadrados() { return metrosCuadrados; }
    public double getPrecio() { return precio; }
    public EstadoStand getEstado() { return estado; }
    public void setEstado(EstadoStand estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("Stand #%d | Área: %.1f m² | Precio: $%.2f | Estado: %s",
                id, metrosCuadrados, precio, estado);
    }
}