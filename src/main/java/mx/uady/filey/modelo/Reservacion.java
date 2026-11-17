package mx.uady.filey.modelo;

import java.time.LocalDate;

public class Reservacion {
    private int id;
    private int idStand;
    private int idEditorial;
    private LocalDate fechaReserva;
    private LocalDate fechaVencimiento;
    private double montoPagado;
    private boolean aplicaProntoPago;

    public Reservacion(int id, int idStand, int idEditorial, LocalDate fechaReserva,
                       LocalDate fechaVencimiento, double montoPagado, boolean aplicaProntoPago) {
        this.id = id;
        this.idStand = idStand;
        this.idEditorial = idEditorial;
        this.fechaReserva = fechaReserva;
        this.fechaVencimiento = fechaVencimiento;
        this.montoPagado = montoPagado;
        this.aplicaProntoPago = aplicaProntoPago;
    }

    public int getId() { return id; }
    public int getIdStand() { return idStand; }
    public int getIdEditorial() { return idEditorial; }
    public LocalDate getFechaReserva() { return fechaReserva; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public double getMontoPagado() { return montoPagado; }
    public boolean isAplicaProntoPago() { return aplicaProntoPago; }

    @Override
    public String toString() {
        return String.format("Reserva #%d | Stand: #%d | Editorial ID: %d | Vence: %s | Pagado: $%.2f | Pronto Pago: %s",
                id, idStand, idEditorial, fechaVencimiento, montoPagado, (aplicaProntoPago ? "Sí" : "No"));
    }
}