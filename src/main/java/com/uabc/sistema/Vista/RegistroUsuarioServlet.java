package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Negocio.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registrarUsuario")
public class RegistroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        Usuario usuario = new Usuario();
        usuario.setNumEmpleado(request.getParameter("NumEmpleado"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidoPaterno(request.getParameter("apellidoPaterno"));
        usuario.setApellidoMaterno(request.getParameter("apellidoMaterno"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena"));

        UsuarioService service = new UsuarioService();
        boolean registrado = service.registrar(usuario);

        if (registrado) {
            // Redirigir directamente a la tabla actualizada
            response.sendRedirect("html/GestionUsuarios.html");
        } else {
            // Si hay duplicado, regresar con parámetro de error
            response.sendRedirect("html/RegistroUsuario.html?error=duplicado");
        }
    }
}


