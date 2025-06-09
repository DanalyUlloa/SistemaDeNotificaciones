package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.HistorialNotificacion;
import com.uabc.sistema.Persistencia.HistorialDao;
import com.google.gson.Gson;
import com.uabc.sistema.Persistencia.db;


import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/obtenerHistorial")
public class ObtenerHistorialServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            Connection conn = db.conectar();

            HistorialDao dao = new HistorialDao(conn);
            List<HistorialNotificacion> historial = dao.obtenerTodos();
            conn.close();

            String json = new Gson().toJson(historial);
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("[]");
        }
    }
}
