package com.uabc.sistema.Persistencia;

import com.uabc.sistema.Entidad.HistorialNotificacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDao {
    private Connection conn;

    public HistorialDao(Connection conn) {
        this.conn = conn;
    }

    public void guardar(HistorialNotificacion h) throws SQLException {
        String sql = "INSERT INTO historial_notificaciones (titulo, contenido, autor, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, h.getTitulo());
            stmt.setString(2, h.getContenido());
            stmt.setString(3, h.getAutor());
            stmt.setTimestamp(4, new Timestamp(h.getFecha().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<HistorialNotificacion> obtenerTodos() throws SQLException {
        List<HistorialNotificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_notificaciones ORDER BY fecha DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HistorialNotificacion h = new HistorialNotificacion();
                h.setId(rs.getInt("id"));
                h.setTitulo(rs.getString("titulo"));
                h.setContenido(rs.getString("contenido"));
                h.setAutor(rs.getString("autor"));
                h.setFecha(rs.getTimestamp("fecha"));
                lista.add(h);
            }
        }
        return lista;
    }
}
