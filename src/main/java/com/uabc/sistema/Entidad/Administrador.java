package com.uabc.sistema.Entidad;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Administrador {
    private int num_empleado;
    private String correo;
    private String nombre;
    private String apellidop;
    private String apellidom;

    public Administrador(int num_empleado, String correo, String nombre, String apellidop, String apellidom) {
        this.num_empleado = num_empleado;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
    }

    public int getNum_empleado() { return num_empleado; }
    public String getcorreo() { return correo; }
    public String getnombre() { return nombre; }
    public String getApellidop() { return apellidop; }
    public String getApellidom() { return apellidom; }
}
