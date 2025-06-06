package com.uabc.sistema.Vista;

import com.google.gson.Gson;
import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Persistencia.NoticiaDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/actualizarNoticia")
public class ActualizarNoticiaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        try (BufferedReader reader = request.getReader();
             Connection conexion = db.conectar()) {

            Gson gson = new Gson();
            Noticia noticia = gson.fromJson(reader, Noticia.class);

            NoticiaDao dao = new NoticiaDao(conexion);
            boolean actualizado = dao.actualizar(noticia);

            if (actualizado) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"mensaje\":\"Noticia actualizada\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Noticia no encontrada\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al actualizar la noticia\"}");
        }
    }
}
