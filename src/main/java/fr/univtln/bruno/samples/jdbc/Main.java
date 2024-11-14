package fr.univtln.bruno.samples.jdbc;

import fr.univtln.bruno.samples.jdbc.dao.AnimatorDAO;
import fr.univtln.bruno.samples.jdbc.dao.DAO;
import lombok.extern.java.Log;

import java.sql.SQLException;

@Log
public class Main {
    public static void main(String[] args) {

            try (DAO<Animator> animatorDAO = new AnimatorDAO();) {
                animatorDAO.findAll(1, 10).content().forEach(System.out::println);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}