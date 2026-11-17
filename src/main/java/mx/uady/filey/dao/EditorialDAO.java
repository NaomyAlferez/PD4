package mx.uady.filey.dao;

import mx.uady.filey.config.ConexionBD;
import mx.uady.filey.modelo.Editorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {

    public List<Editorial> obtenerTodas() {
        List<Editorial> listaEditoriales = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, telefono FROM Editorial";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Editorial editorial = new Editorial(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("correo"),
                        resultado.getString("telefono")
                );
                listaEditoriales.add(editorial);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Editorial: " + e.getMessage());
        }
        return listaEditoriales;
    }
}