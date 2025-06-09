package com.uabc.sistema.Vista;

import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eliminarNoticia")
public class EliminarNoticiaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conexion = db.conectar()) {
            NoticiaDao dao = new NoticiaDao(conexion);
            boolean eliminado = dao.eliminarNoticia(id);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": " + eliminado + "}");
        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANTE: agrega esto para ver errores en consola
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false}");
        }
    }
}