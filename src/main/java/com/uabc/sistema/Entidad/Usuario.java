package com.uabc.sistema.Entidad;

public class Usuario {
    private String NumEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasena;
    private int permisoRegistro;

    // Getters y setters
    public String getNumEmpleado() {
        return NumEmpleado;
    }
    public void setNumEmpleado(String NumEmpleado) {
        this.NumEmpleado = NumEmpleado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getPermisoRegistro() {
        return permisoRegistro;
    }
    public void setPermisoRegistro(int permisoRegistro) {
        this.permisoRegistro = permisoRegistro;
    }

}
