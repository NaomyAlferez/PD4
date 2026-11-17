package mx.uady.filey.controlador;

import mx.uady.filey.dao.ReservacionDAO;
import mx.uady.filey.modelo.Reservacion;
import mx.uady.filey.vista.ConsolaVista;
import java.util.List;

public class ReservacionControlador {
    private final ReservacionDAO reservacionDAO;
    private final ConsolaVista vista;

    public ReservacionControlador(ReservacionDAO reservacionDAO, ConsolaVista vista) {
        this.reservacionDAO = reservacionDAO;
        this.vista = vista;
    }

    public void registrarReservacion(Reservacion reservacion) {
        if (reservacionDAO.insertar(reservacion)) {
            vista.mostrarMensaje("[CRUD - CREATE] Reservación #" + reservacion.getId() + " registrada.");
        }
    }

    public List<Reservacion> listarReservaciones() {
        List<Reservacion> lista = reservacionDAO.obtenerTodas();
        vista.mostrarReservaciones(lista);
        return lista;
    }

    public void actualizarReservacion(Reservacion reservacion) {
        if (reservacionDAO.actualizar(reservacion)) {
            vista.mostrarMensaje("[CRUD - UPDATE] Reservación #" + reservacion.getId() + " actualizada (Pagado: $" + reservacion.getMontoPagado() + ").");
        }
    }

    public void eliminarReservacion(int id) {
        if (reservacionDAO.eliminar(id)) {
            vista.mostrarMensaje("[CRUD - DELETE] Reservación #" + id + " eliminada.");
        }
    }
}