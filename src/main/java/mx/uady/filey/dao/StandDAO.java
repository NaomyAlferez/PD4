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

    public List<Stand> obtenerTodos() {
        List<Stand> listaStands = new ArrayList<>();
        String sql = "SELECT id, metros_cuadrados, precio, estado FROM Stands";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Stand stand = new Stand(
                        resultado.getInt("id"),
                        resultado.getDouble("metros_cuadrados"),
                        resultado.getDouble("precio"),
                        EstadoStand.valueOf(resultado.getString("estado"))
                );
                listaStands.add(stand);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la tabla Stands: " + e.getMessage());
        }
        return listaStands;
    }
}