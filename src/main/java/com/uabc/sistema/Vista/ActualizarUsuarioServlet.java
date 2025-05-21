package com.uabc.sistema.Vista;

import com.uabc.sistema.Negocio.UsuarioService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.*;

@WebServlet("/actualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        String id = json.get("id").getAsString();
        String nombreCompleto = json.get("nombre").getAsString();
        String correo = json.get("correo").getAsString();

        // Separar nombre completo (esto puede adaptarse mejor)
        String[] partes = nombreCompleto.split(" ", 3);
        String nombre = partes.length > 0 ? partes[0] : "";
        String apellidoPaterno = partes.length > 1 ? partes[1] : "";
        String apellidoMaterno = partes.length > 2 ? partes[2] : "";

        UsuarioService service = new UsuarioService();
        boolean actualizado = service.actualizarUsuario(id, nombre, apellidoPaterno, apellidoMaterno, correo);

        if (actualizado) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
