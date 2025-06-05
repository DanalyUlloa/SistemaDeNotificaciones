package com.uabc.sistema.Negocio;

import com.uabc.sistema.Entidad.Usuario;
import com.uabc.sistema.Persistencia.UsuarioDao;
import com.uabc.sistema.Persistencia.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public boolean registrar(Usuario usuario) {
        try (Connection conexion = db.conectar()) {
            UsuarioDao dao = new UsuarioDao(conexion);

            if (dao.existeCorreo(usuario.getCorreo())) {
                System.out.println("❌ Error: Correo ya registrado.");
                return false;
            }

            if (dao.existeNumEmpleado(usuario.getNumEmpleado())) {
                System.out.println("❌ Error: Número de empleado ya registrado.");
                return false;
            }

            return dao.registrarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Nuevo método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        try (Connection conexion = db.conectar()) {
            UsuarioDao dao = new UsuarioDao(conexion);
            return dao.obtenerTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public boolean actualizarUsuario(String id, String nombre, String apeP, String apeM, String correo, int permisoRegistro) {
        try (Connection conexion = db.conectar()) {
            UsuarioDao dao = new UsuarioDao(conexion);
            return dao.actualizarUsuario(id, nombre, apeP, apeM, correo, permisoRegistro);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean eliminarUsuario(String numEmpleado) {
        try (Connection conexion = db.conectar()) {
            UsuarioDao dao = new UsuarioDao(conexion);
            return dao.eliminarUsuario(numEmpleado);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
