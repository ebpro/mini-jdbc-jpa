package fr.univtln.bruno.samples.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animator {
    private UUID id;
    private String name;
    private String email;
}