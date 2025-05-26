package com.uabc.sistema.Persistencia;

import com.uabc.sistema.Entidad.Noticia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticiaDao {
    private Connection conexion;

    public NoticiaDao(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrarNoticia(Noticia noticia) {
        String sql = "INSERT INTO noticia (Titulo, Autor, Contenido, Fecha, Categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getAutor());
            ps.setString(3, noticia.getContenido());
            ps.setString(4, noticia.getFecha());
            ps.setString(5, noticia.getCategoria());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}