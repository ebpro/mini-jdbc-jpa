package fr.univtln.bruno.samples.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DBManager {
    // Singleton instance of DBManager
    private static DBManager instance;
    // Data source for managing database connections
    private final ComboPooledDataSource dataSource;

    // Private constructor to initialize the data source
    private DBManager() throws PropertyVetoException {
        dataSource = new ComboPooledDataSource();
        // Set JDBC URL from environment variable or default value
        dataSource.setJdbcUrl(Optional.ofNullable(System.getenv("DATASOURCE_URL")).orElse("jdbc:postgresql://localhost:5432/radio_db"));
        // Set database user from environment variable or default value
        dataSource.setUser(Optional.ofNullable(System.getenv("DB_USER")).orElse("postgres"));
        // Set database password from environment variable or default value
        dataSource.setPassword(Optional.ofNullable(System.getenv("DB_PASSWORD")).orElse("postgres"));
        // Set JDBC driver class from environment variable or default value
        dataSource.setDriverClass(Optional.ofNullable(System.getenv("DB_DRIVER_CLASS")).orElse("org.postgresql.Driver"));
    }

    // Method to get the singleton instance of DBManager
    public static DBManager getInstance() throws PropertyVetoException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    // Method to get a connection from the data source
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Method to close the data source
    public void close() {
        dataSource.close();
    }
}