package com.uabc.sistema.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema"; // <- tu nombre de base aquí
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga manual del driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
