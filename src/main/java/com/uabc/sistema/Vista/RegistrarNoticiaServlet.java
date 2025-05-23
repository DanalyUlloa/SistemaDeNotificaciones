package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Noticia;
import com.uabc.sistema.Negocio.NoticiaService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registrarNoticia")
public class RegistrarNoticiaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String contenido = request.getParameter("contenido");
        String fecha = request.getParameter("fecha");
        String categoria = request.getParameter("categoria");

        Noticia noticia = new Noticia(titulo, autor, contenido, fecha, categoria);
        boolean exito = new NoticiaService().registrarNoticia(noticia);

        if (exito) {
            response.sendRedirect("GestionNoticias.html");
        } else {
            response.sendRedirect("RegistrarNoticias.html?error=true");
        }
    }
}