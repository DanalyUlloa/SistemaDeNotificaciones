package com.uabc.sistema.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    // Usa el nombre correcto de tu base de datos:
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_notificaciones?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegura que el driver esté cargado
        } catch (ClassNotFoundException e) {
            System.err.println("⚠️ No se encontró el driver de MySQL.");
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos.");
            e.printStackTrace();
            throw e;
        }
    }
}