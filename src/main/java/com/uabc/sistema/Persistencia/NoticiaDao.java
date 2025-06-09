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

    // Registrar noticia
    public boolean registrarNoticia(Noticia noticia) {
        String sql = "INSERT INTO noticia (Titulo, Contenido, Fecha, autor_id, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setString(3, noticia.getFecha());
            ps.setString(4, noticia.getAutorId());
            ps.setInt(5, noticia.getCategoriaId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener todas las noticias
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
                noticia.setId(rs.getInt("NoticiaID"));
                lista.add(noticia);
            }
        }
        return lista;
    }

    // Eliminar noticia por ID
    public boolean eliminarNoticia(int id) throws SQLException {
        String sql = "DELETE FROM noticia WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Actualizar noticia
    public boolean actualizarNoticia(int id, String titulo, String contenido, String fecha) throws SQLException {
        String sql = "UPDATE noticia SET Titulo = ?, Contenido = ?, Fecha = ? WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, contenido);
            ps.setString(3, fecha);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Buscar una noticia por ID (para precargar al editar)
    public Noticia buscarPorId(int id) throws SQLException {
        String sql = "SELECT NoticiaID, Titulo, Contenido, Fecha FROM noticia WHERE NoticiaID = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Noticia noticia = new Noticia(
                            rs.getString("Titulo"),
                            rs.getString("Contenido"),
                            rs.getString("Fecha"),
                            "", "" // autor y categoría no son necesarios aquí
                    );
                    noticia.setId(rs.getInt("NoticiaID"));
                    return noticia;
                }
            }
        }
        return null;
    }
}