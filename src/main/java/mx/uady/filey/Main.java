package mx.uady.filey;

import mx.uady.filey.config.ConfiguracionSistema;
import mx.uady.filey.controlador.EditorialControlador;
import mx.uady.filey.controlador.ReservacionControlador;
import mx.uady.filey.controlador.StandControlador;
import mx.uady.filey.dao.EditorialDAO;
import mx.uady.filey.dao.ReservacionDAO;
import mx.uady.filey.dao.StandDAO;
import mx.uady.filey.modelo.Editorial;
import mx.uady.filey.modelo.EstadoStand;
import mx.uady.filey.modelo.Reservacion;
import mx.uady.filey.modelo.Stand;
import mx.uady.filey.servicio.ServicioNotificaciones;
import mx.uady.filey.vista.ConsolaVista;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfiguracionSistema config = new ConfiguracionSistema();
        ConsolaVista vista = new ConsolaVista();

        // 1. Instanciar DAOs por entidad
        StandDAO standDAO = new StandDAO();
        EditorialDAO editorialDAO = new EditorialDAO();
        ReservacionDAO reservacionDAO = new ReservacionDAO();

        // 2. Instanciar un Controlador por entidad
        StandControlador standCtrl = new StandControlador(standDAO, vista);
        EditorialControlador editorialCtrl = new EditorialControlador(editorialDAO, vista);
        ReservacionControlador reservacionCtrl = new ReservacionControlador(reservacionDAO, vista);

        System.out.println("==================================================================");
        System.out.println("     SISTEMA ADMINISTRATIVO DE STANDS - FILEY (MVC + CRUD)        ");
        System.out.println("==================================================================");

        // --- DEMOSTRACIÓN DE LECTURA INICIAL (READ) ---
        standCtrl.listarStands();
        editorialCtrl.listarEditoriales();
        reservacionCtrl.listarReservaciones();

        // --- DEMOSTRACIÓN DE CREACIÓN, ACTUALIZACIÓN Y BORRADO (C-U-D) ---
        System.out.println("\n--- EJECUTANDO OPERACIONES CRUD POR ENTIDAD ---");
        
        // CREATE
        Stand nuevoStand = new Stand(105, 10.0, 15000.0, EstadoStand.DISPONIBLE);
        standCtrl.registrarStand(nuevoStand);

        Editorial nuevaEditorial = new Editorial(4, "Editorial Sexto Piso", "contacto@sextopiso.mx", "9994445566");
        editorialCtrl.registrarEditorial(nuevaEditorial);

        Reservacion nuevaReserva = new Reservacion(3, 105, 4, LocalDate.now(), LocalDate.now().plusDays(5), 5000.0, true);
        reservacionCtrl.registrarReservacion(nuevaReserva);

        // UPDATE
        nuevoStand.setEstado(EstadoStand.RESERVADO);
        standCtrl.actualizarStand(nuevoStand);

        nuevaReserva.setMontoPagado(10000.0);
        reservacionCtrl.actualizarReservacion(nuevaReserva);

        // DELETE (Eliminamos la reserva de prueba y luego el stand/editorial)
        reservacionCtrl.eliminarReservacion(3);
        standCtrl.eliminarStand(105);
        editorialCtrl.eliminarEditorial(4);

        // --- VERIFICACIÓN FINAL Y NOTIFICACIONES ---
        System.out.println("\n--- ESTADO FINAL DE LAS TABLAS TRAS EL CRUD ---");
        List<Stand> standsFinales = standCtrl.listarStands();
        List<Editorial> editorialesFinales = editorialCtrl.listarEditoriales();
        List<Reservacion> reservasFinales = reservacionCtrl.listarReservaciones();

        ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones(config);
        vista.mostrarNotificaciones(
                servicioNotificaciones.generarAlertas(reservasFinales, standsFinales, editorialesFinales)
        );
    }
}