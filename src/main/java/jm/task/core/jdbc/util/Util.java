package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/new_schema", "root", "root");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
