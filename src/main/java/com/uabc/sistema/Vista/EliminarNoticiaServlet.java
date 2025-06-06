package com.uabc.sistema.Vista;

import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eliminarNoticia")
public class EliminarNoticiaServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID faltante\"}");
            return;
        }

        try (Connection conexion = db.conectar()) {
            int id = Integer.parseInt(idParam);
            NoticiaDao dao = new NoticiaDao(conexion);
            boolean eliminado = dao.eliminar(id);

            if (eliminado) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"mensaje\":\"Noticia eliminada\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Noticia no encontrada\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al eliminar la noticia\"}");
        }
    }
}
