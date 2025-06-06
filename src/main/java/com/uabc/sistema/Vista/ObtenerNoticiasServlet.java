package com.uabc.sistema.Vista;

import com.google.gson.Gson;
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

        // Asegurar que la codificación sea UTF-8 tanto en request como en response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (Connection conexion = db.conectar()) {
            NoticiaDao dao = new NoticiaDao(conexion);
            List<Noticia> lista = dao.obtenerTodas();

            Gson gson = new Gson();
            String json = gson.toJson(lista);

            response.getWriter().write(json);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"No se pudieron obtener las noticias\"}");
        }
    }
}