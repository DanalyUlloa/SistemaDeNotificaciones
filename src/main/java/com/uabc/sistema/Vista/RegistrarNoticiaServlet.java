package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Negocio.NoticiaService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registrarNoticia")
public class RegistrarNoticiaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        String fecha = request.getParameter("fecha");
        String autorId = request.getParameter("autor"); // NumEmpleado del usuario
        String categoriaParam = request.getParameter("categoria");

        try {
            int categoriaId = Integer.parseInt(categoriaParam);

            Noticia noticia = new Noticia(titulo, contenido, fecha, autorId, categoriaId);
            boolean exito = new NoticiaService().registrarNoticia(noticia);

            if (exito) {
                response.sendRedirect("html/GestionNoticias.html?exito=true");
            } else {
                response.sendRedirect("html/RegistroNoticias.html?error=true");
            }

        } catch (NumberFormatException e) {
            // Error si no se puede convertir el ID de categoría
            e.printStackTrace();
            response.sendRedirect("html/RegistroNoticias.html?error=invalidCategoria");
        }
    }
}