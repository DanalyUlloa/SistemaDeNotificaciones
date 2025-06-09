package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/obtenerNoticiaPorId")
public class ObtenerNoticiaPorIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            try (Connection conexion = db.conectar()) {
                NoticiaDao dao = new NoticiaDao(conexion);
                Noticia noticia = dao.buscarPorId(id);

                if (noticia != null) {
                    PrintWriter out = response.getWriter();
                    out.write("{\"titulo\":\"" + noticia.getTitulo() + "\","
                            + "\"contenido\":\"" + noticia.getContenido() + "\","
                            + "\"fecha\":\"" + noticia.getFecha() + "\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Noticia no encontrada\"}");
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error al cargar la noticia\"}");
        }
    }
}