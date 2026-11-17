package mx.uady.filey.dao;

import mx.uady.filey.config.ConexionBD;
import mx.uady.filey.modelo.Reservacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionDAO {

    // CREATE
    public boolean insertar(Reservacion r) {
        String sql = "INSERT INTO Reservacion (id, id_stand, id_editorial, fecha_reserva, fecha_vencimiento, monto_pagado, aplica_pronto_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getId());
            stmt.setInt(2, r.getIdStand());
            stmt.setInt(3, r.getIdEditorial());
            stmt.setDate(4, Date.valueOf(r.getFechaReserva()));
            stmt.setDate(5, Date.valueOf(r.getFechaVencimiento()));
            stmt.setDouble(6, r.getMontoPagado());
            stmt.setBoolean(7, r.isAplicaProntoPago());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar Reservacion: " + e.getMessage());
            return false;
        }
    }

    // READ (Todas)
    public List<Reservacion> obtenerTodas() {
        List<Reservacion> reservaciones = new ArrayList<>();
        String sql = "SELECT id, id_stand, id_editorial, fecha_reserva, fecha_vencimiento, monto_pagado, aplica_pronto_pago FROM Reservacion";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservaciones.add(new Reservacion(
                        rs.getInt("id"),
                        rs.getInt("id_stand"),
                        rs.getInt("id_editorial"),
                        rs.getDate("fecha_reserva").toLocalDate(),
                        rs.getDate("fecha_vencimiento").toLocalDate(),
                        rs.getDouble("monto_pagado"),
                        rs.getBoolean("aplica_pronto_pago")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Reservacion: " + e.getMessage());
        }
        return reservaciones;
    }

    // READ (Por ID)
    public Reservacion obtenerPorId(int id) {
        String sql = "SELECT id, id_stand, id_editorial, fecha_reserva, fecha_vencimiento, monto_pagado, aplica_pronto_pago FROM Reservacion WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservacion(
                            rs.getInt("id"),
                            rs.getInt("id_stand"),
                            rs.getInt("id_editorial"),
                            rs.getDate("fecha_reserva").toLocalDate(),
                            rs.getDate("fecha_vencimiento").toLocalDate(),
                            rs.getDouble("monto_pagado"),
                            rs.getBoolean("aplica_pronto_pago")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Reservacion: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public boolean actualizar(Reservacion r) {
        String sql = "UPDATE Reservacion SET id_stand = ?, id_editorial = ?, fecha_reserva = ?, fecha_vencimiento = ?, monto_pagado = ?, aplica_pronto_pago = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdStand());
            stmt.setInt(2, r.getIdEditorial());
            stmt.setDate(3, Date.valueOf(r.getFechaReserva()));
            stmt.setDate(4, Date.valueOf(r.getFechaVencimiento()));
            stmt.setDouble(5, r.getMontoPagado());
            stmt.setBoolean(6, r.isAplicaProntoPago());
            stmt.setInt(7, r.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar Reservacion: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Reservacion WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Reservacion: " + e.getMessage());
            return false;
        }
    }
}