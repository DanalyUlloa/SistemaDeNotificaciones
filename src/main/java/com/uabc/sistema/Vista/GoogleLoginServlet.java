package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Persistencia.UsuarioDao;
import com.uabc.sistema.Persistencia.db;

import  javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import  java.sql.Connection;
import  java.sql.SQLException;

@WebServlet("/Login")
public class GoogleLoginServlet extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        try {
            Connection conexion = db.conectar();
            UsuarioDao usuarioDao = new UsuarioDao(conexion);

            Usuario usuario = usuarioDao.autenticar(correo, contrasena);

            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("html/MenuPrincipal.jsp");
            } else {
                response.sendRedirect("html/Login.html?error=credenciales");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("html/Login.html?error=servidor");
        }
    }
}