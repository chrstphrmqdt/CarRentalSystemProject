package com.example.carrentalsys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class database {
    public static Connection connectdb () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/carrental", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
