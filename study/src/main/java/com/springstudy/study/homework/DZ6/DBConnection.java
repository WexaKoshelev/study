package com.springstudy.study.homework.DZ6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.springstudy.study.homework.DZ6.DBConstants.*;

public enum DBConnection  {
    INSTANCE;
    private Connection connection;

    public Connection newConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + PORT + "/" +
                    DB, USER, PASSWORD);
        }
        return connection;
    }

}
