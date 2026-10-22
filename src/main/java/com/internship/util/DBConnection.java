package com.internship.util;

import java.sql.Connection;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "SYSTEM";
    private static final String PASSWORD = "system123";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Oracle Database Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}