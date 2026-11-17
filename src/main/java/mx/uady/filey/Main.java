package mx.uady.filey;

import mx.uady.filey.config.ConfiguracionSistema;
import mx.uady.filey.controlador.SistemaControlador;
import mx.uady.filey.dao.EditorialDAO;
import mx.uady.filey.dao.ReservacionDAO;
import mx.uady.filey.dao.StandDAO;
import mx.uady.filey.servicio.ServicioNotificaciones;
import mx.uady.filey.vista.ConsolaVista;

public class Main {
    public static void main(String[] args) {
        ConfiguracionSistema config = new ConfiguracionSistema();

        StandDAO standDAO = new StandDAO();
        EditorialDAO editorialDAO = new EditorialDAO();
        ReservacionDAO reservacionDAO = new ReservacionDAO();

        ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones(config);
        ConsolaVista vista = new ConsolaVista();

        SistemaControlador controlador = new SistemaControlador(
                standDAO, editorialDAO, reservacionDAO, servicioNotificaciones, vista
        );

        controlador.iniciarDemostracionLectura();
    }
}