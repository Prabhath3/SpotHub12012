package com.example.employeemangementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnector {

    private static final String URL = "jdbc:mysql://mysql-3ef5529b-spothubapp.c.aivencloud.com:19543/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_9bs-a7IQUN0nGIiV3YJ";

    private static Connection connection = null;

    private databaseConnector() {}


    public static Connection getConnection() {
        if (connection == null) {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
