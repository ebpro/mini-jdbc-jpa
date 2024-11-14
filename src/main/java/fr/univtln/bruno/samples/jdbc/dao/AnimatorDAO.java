package fr.univtln.bruno.samples.jdbc.dao;

import fr.univtln.bruno.samples.jdbc.Animator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimatorDAO extends AbstractDAO<Animator> {

    public AnimatorDAO() throws SQLException {
    }

    @Override
    protected Animator mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Animator(
                resultSet.getObject("id", java.util.UUID.class),
                resultSet.getString("name"),
                resultSet.getString("email")
        );
    }

    @Override
    protected String getTableName() {
        return "animators";
    }

}
