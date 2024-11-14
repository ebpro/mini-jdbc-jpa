package fr.univtln.bruno.samples.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
@NamedQuery(name="Song.findByTitle", query="SELECT s FROM Song s WHERE s.title = :title")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Artist author;
}