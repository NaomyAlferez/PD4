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

    // CREATE
    public boolean insertar(Editorial editorial) {
        String sql = "INSERT INTO Editorial (id, nombre, correo, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, editorial.getId());
            stmt.setString(2, editorial.getNombre());
            stmt.setString(3, editorial.getCorreo());
            stmt.setString(4, editorial.getTelefono());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar Editorial: " + e.getMessage());
            return false;
        }
    }

    // READ (Todas)
    public List<Editorial> obtenerTodas() {
        List<Editorial> editoriales = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, telefono FROM Editorial";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                editoriales.add(new Editorial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Editorial: " + e.getMessage());
        }
        return editoriales;
    }

    // READ (Por ID)
    public Editorial obtenerPorId(int id) {
        String sql = "SELECT id, nombre, correo, telefono FROM Editorial WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Editorial(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Editorial: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public boolean actualizar(Editorial editorial) {
        String sql = "UPDATE Editorial SET nombre = ?, correo = ?, telefono = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, editorial.getNombre());
            stmt.setString(2, editorial.getCorreo());
            stmt.setString(3, editorial.getTelefono());
            stmt.setInt(4, editorial.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar Editorial: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Editorial WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Editorial: " + e.getMessage());
            return false;
        }
    }
}