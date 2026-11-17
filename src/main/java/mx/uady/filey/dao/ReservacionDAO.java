package mx.uady.filey.dao;

import mx.uady.filey.config.ConexionBD;
import mx.uady.filey.modelo.Reservacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionDAO {

    public List<Reservacion> obtenerTodas() {
        List<Reservacion> listaReservaciones = new ArrayList<>();
        String sql = "SELECT id, id_stand, id_editorial, fecha_reserva, fecha_vencimiento, monto_pagado, aplica_pronto_pago FROM Reservacion";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Reservacion reserva = new Reservacion(
                        resultado.getInt("id"),
                        resultado.getInt("id_stand"),
                        resultado.getInt("id_editorial"),
                        resultado.getDate("fecha_reserva").toLocalDate(),
                        resultado.getDate("fecha_vencimiento").toLocalDate(),
                        resultado.getDouble("monto_pagado"),
                        resultado.getBoolean("aplica_pronto_pago")
                );
                listaReservaciones.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Reservacion: " + e.getMessage());
        }
        return listaReservaciones;
    }
}