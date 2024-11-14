package fr.univtln.bruno.samples.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
             EntityManager em = emf.createEntityManager()) {

                em.getTransaction().begin();

                Artist a=Artist.builder().name("Mozart").build();
                em.persist(a);
                em.persist(Song.builder().title("Symphony No. 40").author(a).build());

                em.persist(Song.builder().title("Fifth Symphony")
                        .author(Artist.builder().name("Beethoven").build()).build());

                em.getTransaction().commit();

                em.createNamedQuery("Song.findAll", Song.class).getResultList().forEach(System.out::println);
        }
    }
}
