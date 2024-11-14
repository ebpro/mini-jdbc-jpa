package fr.univtln.bruno.samples.jdbc.dao;

import fr.univtln.bruno.samples.jdbc.DBManager;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractDAO<T> implements DAO<T> {

    // Prepared statements for various database operations
    private final PreparedStatement countStatement;
    private final PreparedStatement findByIdStatement;
    private final PreparedStatement findAllStatement;
    private final Connection connection;

    // Constructor initializes the connection and prepared statements
    public AbstractDAO() throws SQLException {
        this.connection = getConnection();
        this.findByIdStatement = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id = ?");
        this.findAllStatement = connection.prepareStatement("SELECT * FROM " + getTableName() + " LIMIT ? OFFSET ?");
        this.countStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + getTableName());
    }

    // Method to get a connection from the DBManager
    protected Connection getConnection() throws SQLException {
        try {
            return DBManager.getInstance().getConnection();
        } catch (PropertyVetoException e) {
            throw new SQLException("Failed to get connection from DBManager", e);
        }
    }

    // Abstract method to map a ResultSet to an entity of type T
    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    // Abstract method to get the table name for the DAO
    protected abstract String getTableName();

    // Method to find an entity by its UUID
    public Optional<T> find(UUID id) throws SQLException {
        findByIdStatement.setObject(1, id);
        try (ResultSet resultSet = findByIdStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(mapResultSetToEntity(resultSet));
            }
        }
        return Optional.empty();
    }

    // Method to find all entities with pagination
    public Page<T> findAll(int pageNumber, int pageSize) throws SQLException {
        int offset = (pageNumber - 1) * pageSize;
        findAllStatement.setInt(1, pageSize);
        findAllStatement.setInt(2, offset);
        List<T> entities = new ArrayList<>();
        try (ResultSet resultSet = findAllStatement.executeQuery()) {
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        }
        long totalElements = getTotalElements();
        return new Page<>(entities, pageNumber, pageSize, totalElements);
    }

    // Method to get the total number of elements in the table
    private long getTotalElements() throws SQLException {
        try (ResultSet resultSet = countStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        }
        return 0;
    }

    // Method to close the connection
    public void close() throws SQLException {
        connection.close();
    }
}