package com.uabc.sistema.Persistencia;

import com.uabc.sistema.Entidad.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection conexion;

    public UsuarioDao(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (NumEmpleado, nombre, apellidoPaterno, apellidoMaterno, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNumEmpleado());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellidoPaterno());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT 1 FROM usuarios WHERE correo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeNumEmpleado(String numEmpleado) {
        String sql = "SELECT 1 FROM usuarios WHERE NumEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, numEmpleado);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT NumEmpleado, nombre, apellidoPaterno, apellidoMaterno, correo FROM usuarios";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNumEmpleado(rs.getString("NumEmpleado"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidoPaterno(rs.getString("apellidoPaterno"));
                u.setApellidoMaterno(rs.getString("apellidoMaterno"));
                u.setCorreo(rs.getString("correo"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public boolean actualizarUsuario(String numEmpleado, String nombre, String apeP, String apeM, String correo) {
        String sql = "UPDATE usuarios SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, correo=? WHERE NumEmpleado=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apeP);
            ps.setString(3, apeM);
            ps.setString(4, correo);
            ps.setString(5, numEmpleado);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarUsuario(String numEmpleado) {
        String sql = "DELETE FROM usuarios WHERE NumEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, numEmpleado);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
