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

        int permisoRegistro = 0;
        try {
            if (json.has("permisoRegistro")) {
                JsonElement el = json.get("permisoRegistro");
                if (el.isJsonPrimitive()) {
                    JsonPrimitive prim = el.getAsJsonPrimitive();
                    if (prim.isBoolean()) {
                        permisoRegistro = prim.getAsBoolean() ? 1 : 0;
                    } else if (prim.isNumber()) {
                        permisoRegistro = prim.getAsInt();
                    } else if (prim.isString()) {
                        String val = prim.getAsString();
                        permisoRegistro = val.equalsIgnoreCase("true") || val.equals("1") ? 1 : 0;
                    }
                }
            }
            }catch (Exception e) {
            e.printStackTrace();
            permisoRegistro = 0;
        }

        // Separar nombre completo
        String[] partes = nombreCompleto.split(" ", 3);
        String nombre = partes.length > 0 ? partes[0] : "";
        String apellidoPaterno = partes.length > 1 ? partes[1] : "";
        String apellidoMaterno = partes.length > 2 ? partes[2] : "";

        UsuarioService service = new UsuarioService();
        boolean actualizado = service.actualizarUsuario(id, nombre, apellidoPaterno, apellidoMaterno, correo, permisoRegistro);

        if (actualizado) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
