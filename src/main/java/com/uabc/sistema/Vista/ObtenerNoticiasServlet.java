package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/obtenerNoticias")
public class ObtenerNoticiasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (Connection conexion = db.conectar()) {
            NoticiaDao dao = new NoticiaDao(conexion);
            List<Noticia> lista = dao.obtenerTodas();

            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < lista.size(); i++) {
                Noticia noticia = lista.get(i);
                json.append("{")
                        .append("\"id\":").append(noticia.getId()).append(",")
                        .append("\"titulo\":\"").append(escape(noticia.getTitulo())).append("\",")
                        .append("\"contenido\":\"").append(escape(noticia.getContenido())).append("\",")
                        .append("\"fecha\":\"").append(noticia.getFecha()).append("\",")
                        .append("\"autor\":\"").append(escape(noticia.getAutor())).append("\",")
                        .append("\"categoria\":\"").append(escape(noticia.getCategoria())).append("\"")
                        .append("}");
                if (i < lista.size() - 1) {
                    json.append(",");
                }
            }

            json.append("]");
            response.getWriter().write(json.toString());

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"No se pudieron obtener las noticias\"}");
        }
    }

    // Método auxiliar en JSON
    private String escape(String texto) {
        return texto == null ? "" : texto.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }
}