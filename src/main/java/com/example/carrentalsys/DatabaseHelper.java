package com.example.carrentalsys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseHelper {
    private Connection conn;
    private PreparedStatement prepare;
    private ResultSet result;

    public DatabaseHelper() {
        conn = database.connectdb();
    }

    public ResultSet getUserInfo(String username, String password, String userType) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND user_type = ?";

        try {
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setString(3, userType);
            result = prepare.executeQuery();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

