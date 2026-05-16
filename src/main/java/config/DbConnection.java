package config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() {


        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:6767/ite_db",
                    "qwer",
                    "qwer123"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
