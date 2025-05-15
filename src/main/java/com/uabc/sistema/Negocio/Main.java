package com.uabc.sistema.Negocio;


import com.uabc.sistema.Persistencia.AdministradorDao;

public class Main {
    public static void main(String[] args) {
        String correoBuscado = "bautista.quintero@uabc.edu.mx";

        AdministradorDao repo = new AdministradorDao();
        src.main.java.Entidad.Administrador admin = repo.obtenerPorCorreo(correoBuscado);

        if (admin != null) {
            System.out.println("Bienvenido, " + admin.getnombre());
            System.out.println("Número de empleado: " + admin.getNum_empleado());
        } else {
            System.out.println("No se encontró ningún administrador con ese correo.");
        }
    }
}
