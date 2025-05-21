package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Negocio.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/obtenerUsuarios")
public class ObtenerUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioService servicio = new UsuarioService();
        List<Usuario> usuarios = servicio.obtenerTodos();

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("[");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            out.print("{");
            out.print("\"id\":\"" + u.getNumEmpleado() + "\",");
            out.print("\"nombre\":\"" + u.getNombre() + " " + u.getApellidoPaterno() + " " + u.getApellidoMaterno() + "\",");
            out.print("\"correo\":\"" + u.getCorreo() + "\"");
            out.print("}");
            if (i < usuarios.size() - 1) out.print(",");
        }
        out.println("]");
        out.close();
    }
}
