package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Entidad.HistorialNotificacion;
import com.uabc.sistema.Persistencia.UsuarioDao;
import com.uabc.sistema.Persistencia.HistorialDao;
import com.uabc.sistema.Persistencia.db;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@WebServlet("/publicarNoticia")
public class PublicarNoticiaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder jsonBuffer = new StringBuilder();
        String linea;
        try (BufferedReader reader = req.getReader()) {
            while ((linea = reader.readLine()) != null) {
                jsonBuffer.append(linea);
            }
        }

        try {
            JSONObject json = new JSONObject(jsonBuffer.toString());
            String titulo = json.getString("titulo");
            String contenido = json.getString("contenido");

            try (Connection conexion = db.conectar()) {
                UsuarioDao dao = new UsuarioDao(conexion);
                List<Usuario> usuarios = dao.obtenerTodos();

                for (Usuario usuario : usuarios) {
                    enviarCorreo(usuario.getCorreo(), titulo, contenido);
                }

                // Guardar en historial de notificaciones
                HistorialNotificacion h = new HistorialNotificacion();
                h.setTitulo(titulo);
                h.setContenido(contenido);
                h.setAutor("Administrador"); // Puedes ajustar si se obtiene de sesión
                h.setFecha(new Date());

                HistorialDao historialDao = new HistorialDao(conexion);
                historialDao.guardar(h);

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Noticia enviada y guardada en el historial\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"No se pudo enviar la noticia\"}");
        }
    }

    private void enviarCorreo(String destino, String titulo, String contenido) throws MessagingException {
        final String remitente = "alberto.alapisco@uabc.edu.mx";
        final String contrasena = "sybe ltho gdkm mtqs"; // Usa variables de entorno en producción

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contrasena);
            }
        });

        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
        mensaje.setSubject("📢 Nueva Noticia: " + titulo);
        mensaje.setText(contenido);

        Transport.send(mensaje);
    }
}
