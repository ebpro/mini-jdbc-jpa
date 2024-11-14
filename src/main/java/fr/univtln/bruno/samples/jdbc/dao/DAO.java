package fr.univtln.bruno.samples.jdbc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// DAO interface for generic type T, extends AutoCloseable to ensure resources can be closed
public interface DAO<T> extends AutoCloseable {

    // Record to represent a page of results with pagination details
    record Page<T>(List<T> content, int pageNumber, int pageSize, long totalElements) {

        // Method to calculate the total number of pages
        public int getTotalPages() {
            return (int) Math.ceil((double) totalElements / pageSize);
        }

        // Method to check if there is a next page
        public boolean hasNext() {
            return pageNumber < getTotalPages() - 1;
        }

        // Method to check if there is a previous page
        public boolean hasPrevious() {
            return pageNumber > 0;
        }
    }

    // Method to find all entities with pagination
    Page<T> findAll(int pageNumber, int pageSize) throws SQLException;

    // Method to find an entity by its UUID
    Optional<T> find(UUID id) throws SQLException;
}