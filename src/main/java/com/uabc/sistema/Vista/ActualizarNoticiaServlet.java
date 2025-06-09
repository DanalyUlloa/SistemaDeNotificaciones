package com.uabc.sistema.Vista;

import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/actualizarNoticia")
public class ActualizarNoticiaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String contenido = request.getParameter("contenido");
            String fecha = request.getParameter("fecha");

            try (Connection conexion = db.conectar()) {
                NoticiaDao dao = new NoticiaDao(conexion);
                boolean actualizado = dao.actualizarNoticia(id, titulo, contenido, fecha);

                PrintWriter out = response.getWriter();
                out.write("{\"success\": " + actualizado + "}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error al actualizar noticia\"}");
        }
    }
}