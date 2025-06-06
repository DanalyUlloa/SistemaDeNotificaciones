package com.uabc.sistema.Persistencia;

import com.uabc.sistema.Entidad.Noticia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDao {
    private Connection conexion;

    public NoticiaDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Registrar una noticia en la base de datos
    public boolean registrarNoticia(Noticia noticia) {
        String sql = "INSERT INTO noticia (Titulo, Contenido, Fecha, autor_id, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setString(3, noticia.getFecha());
            ps.setString(4, noticia.getAutorId());         // autor_id (NumEmpleado)
            ps.setInt(5, noticia.getCategoriaId());        // ID de la categoría
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todas las noticias incluyendo su ID
    public List<Noticia> obtenerTodas() throws SQLException {
        String sql = "SELECT n.NoticiaID, n.Titulo, n.Contenido, n.Fecha, " +
                "u.nombre AS autor, c.Nombre AS categoria " +
                "FROM noticia n " +
                "JOIN usuarios u ON n.autor_id = u.NumEmpleado " +
                "JOIN categoria c ON n.categoria_id = c.CategoriaID";

        List<Noticia> lista = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Noticia noticia = new Noticia(
                        rs.getString("Titulo"),
                        rs.getString("Contenido"),
                        rs.getString("Fecha"),
                        rs.getString("autor"),
                        rs.getString("categoria")
                );
                noticia.setId(rs.getInt("NoticiaID")); // Asignamos el ID
                lista.add(noticia);
            }
        }
        return lista;
    }

    // Eliminar una noticia por ID
    public boolean eliminarNoticia(int id) throws SQLException {
        String sql = "DELETE FROM noticia WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}