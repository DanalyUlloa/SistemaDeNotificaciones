package com.uabc.sistema.Vista;

import com.uabc.sistema.Negocio.UsuarioService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.*;

@WebServlet("/eliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        String id = json.get("id").getAsString();

        UsuarioService service = new UsuarioService();
        boolean eliminado = service.eliminarUsuario(id);

        if (eliminado) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
