package mx.uady.filey.controlador;

import mx.uady.filey.dao.EditorialDAO;
import mx.uady.filey.modelo.Editorial;
import mx.uady.filey.vista.ConsolaVista;
import java.util.List;

public class EditorialControlador {
    private final EditorialDAO editorialDAO;
    private final ConsolaVista vista;

    public EditorialControlador(EditorialDAO editorialDAO, ConsolaVista vista) {
        this.editorialDAO = editorialDAO;
        this.vista = vista;
    }

    public void registrarEditorial(Editorial editorial) {
        if (editorialDAO.insertar(editorial)) {
            vista.mostrarMensaje("[CRUD - CREATE] Editorial '" + editorial.getNombre() + "' registrada.");
        }
    }

    public List<Editorial> listarEditoriales() {
        List<Editorial> lista = editorialDAO.obtenerTodas();
        vista.mostrarEditoriales(lista);
        return lista;
    }

    public void actualizarEditorial(Editorial editorial) {
        if (editorialDAO.actualizar(editorial)) {
            vista.mostrarMensaje("[CRUD - UPDATE] Editorial #" + editorial.getId() + " actualizada.");
        }
    }

    public void eliminarEditorial(int id) {
        if (editorialDAO.eliminar(id)) {
            vista.mostrarMensaje("[CRUD - DELETE] Editorial #" + id + " eliminada.");
        }
    }
}