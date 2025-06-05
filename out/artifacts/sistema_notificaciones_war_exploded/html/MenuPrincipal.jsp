<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ page import="com.uabc.sistema.Entidad.Usuario" %>
<%
  Usuario usuario = (Usuario) session.getAttribute("usuario");
  if (usuario == null) {
    response.sendRedirect("Login.html");
    return;
  }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
    <title>Menú Principal como Administrador</title>
  <link rel="stylesheet" href="../css/menuprincipal.css">
</head>
  <body>
    <div class="container">

      <!-- Barra superior -->
      <div class="header" style="position: relative;">
        <div class="header-title">Gestor de Notificaciones</div>

        <!-- Botón de cerrar sesión -->
        <div class="logout" style="
             position: absolute;
             top: 10px;
             right: 20px;
             display: flex;
             align-items: center;
             gap: 10px;
             font-size: 14px;
             font-weight: normal;">
          <img src="../img/CerrarSesion.png"
               alt="Cerrar sesión"
               title="Cerrar sesión"
               style="width: 40px; height: 40px; cursor: pointer;"
               onclick="location.href='Login.html'">
          <span style="white-space: nowrap;">
            Bienvenid@ <%= usuario.getNombre() %> (<%= usuario.getCorreo() %>)
          </span>
        </div>
      </div>

      <!-- Barra lateral izquierda -->
      <div class="sidebar">
        <% if (usuario.getPermisoRegistro() == 1) { %>
        <div class="menu-item" onclick="location.href='GestionUsuarios.html'">
          <div class="icon">
            <img src="../img/usuario.png"
                 alt="Gestión de Usuarios"
                 class="icon-img"></div>
          <div class="text">Gestión de Usuarios</div>
        </div>
        <% } %>

        <div class="menu-item" onclick="location.href='GestionNoticias.html'">
          <div class="icon">
            <img src="../img/noticiasG.png"
                 alt="Gestión de Noticias"
                 class="icon-img"></div>
          <div class="text">Gestión de Noticias</div>
        </div>
      </div>

      <!-- Contenido principal -->
      <div class="main-content">
        <div class="notification-box">
          <div class="box-title">Historial de publicaciones</div>
          <div class="box-content">
            <img src="../img/cimarron.png"
                 alt="Cimarrón"
                 class="image">
          </div>
        </div>
      </div>

      <!-- Barra inferior derecha con ayuda -->
      <div class="help-box" onclick="location.href='Ayuda.html'">
        <div class="help-icon"><img src="../img/ayuda.png" alt="Ayuda" class="icon-img"></div>
        <div class="help-text">Ayuda</div>
      </div>
    </div>
  </body>
</html>