package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Persistencia.UsuarioDao;
import com.uabc.sistema.Persistencia.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        try (Connection conexion = db.conectar()) {
            UsuarioDao usuarioDao = new UsuarioDao(conexion);
            Usuario usuario = usuarioDao.autenticar(correo, contrasena);

            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);

                // Enviar datos a sessionStorage vía script
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html><head><meta charset='UTF-8'><title>Redirigiendo...</title></head><body>");
                out.println("<script>");
                out.println("sessionStorage.setItem('permisoRegistro', '" + usuario.getPermisoRegistro() + "');");
                out.println("sessionStorage.setItem('nombreUsuario', '" + usuario.getNombre() + "');");
                out.println("sessionStorage.setItem('correoUsuario', '" + usuario.getCorreo() + "');");
                out.println("sessionStorage.setItem('numEmpleado', '" + usuario.getNumEmpleado() + "');");
                out.println("window.location.href = 'html/MenuPrincipal.jsp';");
                out.println("</script>");
                out.println("</body></html>");
            } else {
                response.sendRedirect("html/Login.html?error=credenciales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("html/Login.html?error=servidor");
        }
    }
}