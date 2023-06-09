package org.krupanych.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbUtil {
    private static final String URL = PropertiesUtil.getProperties("db.url");
    private static final String USER_NAME = PropertiesUtil.getProperties("db.username");
    private static final String PASSWORD = PropertiesUtil.getProperties("db.password");

    private DbUtil(){}

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
