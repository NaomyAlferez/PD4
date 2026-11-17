package mx.uady.filey.controlador;

import mx.uady.filey.dao.StandDAO;
import mx.uady.filey.modelo.Stand;
import mx.uady.filey.vista.ConsolaVista;
import java.util.List;

public class StandControlador {
    private final StandDAO standDAO;
    private final ConsolaVista vista;

    public StandControlador(StandDAO standDAO, ConsolaVista vista) {
        this.standDAO = standDAO;
        this.vista = vista;
    }

    public void registrarStand(Stand stand) {
        if (standDAO.insertar(stand)) {
            vista.mostrarMensaje("[CRUD - CREATE] Stand #" + stand.getId() + " creado correctamente.");
        }
    }

    public List<Stand> listarStands() {
        List<Stand> lista = standDAO.obtenerTodos();
        vista.mostrarStands(lista);
        return lista;
    }

    public void actualizarStand(Stand stand) {
        if (standDAO.actualizar(stand)) {
            vista.mostrarMensaje("[CRUD - UPDATE] Stand #" + stand.getId() + " actualizado a estado: " + stand.getEstado());
        }
    }

    public void eliminarStand(int id) {
        if (standDAO.eliminar(id)) {
            vista.mostrarMensaje("[CRUD - DELETE] Stand #" + id + " eliminado correctamente.");
        }
    }
}