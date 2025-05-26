package com.uabc.sistema.Persistencia;

import com.uabc.sistema.Entidad.Administrador;

import java.sql.*;

public class AdministradorDao {
    public Administrador obtenerPorCorreo(String correo) {
        Administrador admin = null;
        try {
            Connection conn = db.conectar();
            String query = "SELECT * FROM administrador WHERE correo = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Administrador(
                        rs.getInt("num_empleado"),
                        rs.getString("correo"),
                        rs.getString("nombre"),
                        rs.getString("apellidop"),
                        rs.getString("apellidom")
                );
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
