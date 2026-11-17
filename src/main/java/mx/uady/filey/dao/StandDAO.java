package mx.uady.filey.dao;

import mx.uady.filey.config.ConexionBD;
import mx.uady.filey.modelo.EstadoStand;
import mx.uady.filey.modelo.Stand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandDAO {

    // CREATE
    public boolean insertar(Stand stand) {
        String sql = "INSERT INTO Stands (id, metros_cuadrados, precio, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stand.getId());
            stmt.setDouble(2, stand.getMetrosCuadrados());
            stmt.setDouble(3, stand.getPrecio());
            stmt.setString(4, stand.getEstado().name());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar Stand: " + e.getMessage());
            return false;
        }
    }

    // READ (Todos)
    public List<Stand> obtenerTodos() {
        List<Stand> stands = new ArrayList<>();
        String sql = "SELECT id, metros_cuadrados, precio, estado FROM Stands";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stands.add(new Stand(
                        rs.getInt("id"),
                        rs.getDouble("metros_cuadrados"),
                        rs.getDouble("precio"),
                        EstadoStand.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Stands: " + e.getMessage());
        }
        return stands;
    }

    // READ (Por ID)
    public Stand obtenerPorId(int id) {
        String sql = "SELECT id, metros_cuadrados, precio, estado FROM Stands WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Stand(
                            rs.getInt("id"),
                            rs.getDouble("metros_cuadrados"),
                            rs.getDouble("precio"),
                            EstadoStand.valueOf(rs.getString("estado"))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Stand: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public boolean actualizar(Stand stand) {
        String sql = "UPDATE Stands SET metros_cuadrados = ?, precio = ?, estado = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, stand.getMetrosCuadrados());
            stmt.setDouble(2, stand.getPrecio());
            stmt.setString(3, stand.getEstado().name());
            stmt.setInt(4, stand.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar Stand: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Stands WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Stand: " + e.getMessage());
            return false;
        }
    }
}