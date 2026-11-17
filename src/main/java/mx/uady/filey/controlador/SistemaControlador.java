package mx.uady.filey.controlador;

import mx.uady.filey.config.ConexionBD;
import mx.uady.filey.dao.EditorialDAO;
import mx.uady.filey.dao.ReservacionDAO;
import mx.uady.filey.dao.StandDAO;
import mx.uady.filey.modelo.Editorial;
import mx.uady.filey.modelo.Reservacion;
import mx.uady.filey.modelo.Stand;
import mx.uady.filey.servicio.ServicioNotificaciones;
import mx.uady.filey.vista.ConsolaVista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SistemaControlador {
    private StandDAO standDAO;
    private EditorialDAO editorialDAO;
    private ReservacionDAO reservacionDAO;
    private ServicioNotificaciones servicioNotificaciones;
    private ConsolaVista vista;

    public SistemaControlador(StandDAO standDAO, EditorialDAO editorialDAO,
                              ReservacionDAO reservacionDAO, ServicioNotificaciones servicioNotificaciones,
                              ConsolaVista vista) {
        this.standDAO = standDAO;
        this.editorialDAO = editorialDAO;
        this.reservacionDAO = reservacionDAO;
        this.servicioNotificaciones = servicioNotificaciones;
        this.vista = vista;
    }

    public void iniciarDemostracionLectura() {
        boolean conectado = probarConexion();
        vista.mostrarEstadoConexion(conectado);

        if (!conectado) return;

        // 1. Lectura de tablas mediante JDBC
        List<Stand> stands = standDAO.obtenerTodos();
        List<Editorial> editoriales = editorialDAO.obtenerTodas();
        List<Reservacion> reservaciones = reservacionDAO.obtenerTodas();

        // 2. Presentación en la Vista de las 3 listas solicitadas en PD4
        vista.mostrarStands(stands);
        vista.mostrarEditoriales(editoriales);
        vista.mostrarReservaciones(reservaciones);

        // 3. Ejecución de las notificaciones de la PD2
        List<String> alertas = servicioNotificaciones.generarAlertas(reservaciones, stands, editoriales);
        vista.mostrarNotificaciones(alertas);
    }

    private boolean probarConexion() {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}