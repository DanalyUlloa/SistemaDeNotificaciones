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

    // Registrar una noticia
    public boolean registrarNoticia(Noticia noticia) {
        String sql = "INSERT INTO noticia (Titulo, Contenido, Fecha, autor_id, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setString(3, noticia.getFecha());
            ps.setString(4, noticia.getAutorId());       // NumEmpleado del autor
            ps.setInt(5, noticia.getCategoriaId());      // ID de categoría
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("NoticiaID"));
                noticia.setTitulo(rs.getString("Titulo"));
                noticia.setContenido(rs.getString("Contenido"));
                noticia.setFecha(rs.getString("Fecha"));
                noticia.setAutor(rs.getString("autor"));         // Nombre del autor
                noticia.setCategoria(rs.getString("categoria")); // Nombre de la categoría
                lista.add(noticia);
            }
        }

        return lista;
    }

    // Actualizar una noticia
    public boolean actualizar(Noticia noticia) throws SQLException {
        String sql = "UPDATE noticia SET Titulo = ?, Contenido = ?, Fecha = ?, autor_id = ?, categoria_id = ? WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setString(3, noticia.getFecha());
            ps.setString(4, noticia.getAutorId());        // NumEmpleado del autor
            ps.setInt(5, noticia.getCategoriaId());       // ID de categoría
            ps.setInt(6, noticia.getId());                // ID de la noticia
            return ps.executeUpdate() > 0;
        }
    }

    // Eliminar una noticia
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM noticia WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
