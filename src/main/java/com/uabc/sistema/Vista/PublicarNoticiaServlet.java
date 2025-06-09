package com.uabc.sistema.Vista;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Persistencia.UsuarioDao;
import com.uabc.sistema.Persistencia.db;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

@WebServlet("/publicarNoticia")
public class PublicarNoticiaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

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

            System.out.println("Título recibido: " + titulo);
            System.out.println("Contenido recibido: " + contenido);

            try (Connection conexion = db.conectar()) {
                UsuarioDao dao = new UsuarioDao(conexion);
                List<Usuario> usuarios = dao.obtenerTodos();

                for (Usuario usuario : usuarios) {
                    if (usuario.getCorreo() != null && !usuario.getCorreo().isEmpty()) {
                        enviarCorreo(usuario.getCorreo(), titulo, contenido);
                        System.out.println("Correo enviado a: " + usuario.getCorreo());
                    }
                }

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Noticia enviada por correo\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"No se pudo enviar la noticia\"}");
        }
    }

    private void enviarCorreo(String destino, String titulo, String contenido) throws MessagingException {
        final String remitente = "alberto.alapisco@uabc.edu.mx";
        final String contrasena = "sybe ltho gdkm mtqs";

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