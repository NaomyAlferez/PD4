package mx.uady.filey.vista;

import mx.uady.filey.modelo.Editorial;
import mx.uady.filey.modelo.Reservacion;
import mx.uady.filey.modelo.Stand;

import java.util.List;

public class ConsolaVista {

    public void mostrarEstadoConexion(boolean exito) {
        System.out.println("=========================================================================");
        System.out.println("          SISTEMA ADMINISTRATIVO DE STANDS - FILEY (MVC + JDBC)          ");
        System.out.println("=========================================================================");
        if (exito) {
            System.out.println("[BD] Conexión exitosa a la base de datos H2 mediante JDBC.\n");
        } else {
            System.out.println("[BD] Error al conectar con la base de datos.\n");
        }
    }

    public void mostrarStands(List<Stand> stands) {
        System.out.println("--- LISTA DE STANDS (Tabla: Stands) ---");
        for (Stand s : stands) {
            System.out.println("  " + s);
        }
        System.out.println();
    }

    public void mostrarEditoriales(List<Editorial> editoriales) {
        System.out.println("--- LISTA DE EDITORIALES (Tabla: Editorial) ---");
        for (Editorial e : editoriales) {
            System.out.println("  " + e);
        }
        System.out.println();
    }

    public void mostrarReservaciones(List<Reservacion> reservaciones) {
        System.out.println("--- LISTA DE RESERVACIONES (Tabla: Reservacion) ---");
        for (Reservacion r : reservaciones) {
            System.out.println("  " + r);
        }
        System.out.println();
    }

    public void mostrarNotificaciones(List<String> alertas) {
        System.out.println("--- NOTIFICACIONES DEL SISTEMA (Lógica de Negocio PD2) ---");
        if (alertas.isEmpty()) {
            System.out.println("  No hay notificaciones pendientes.");
        } else {
            for (String alerta : alertas) {
                System.out.println("  " + alerta);
            }
        }
        System.out.println("=========================================================================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("  -> " + mensaje);
    }
}