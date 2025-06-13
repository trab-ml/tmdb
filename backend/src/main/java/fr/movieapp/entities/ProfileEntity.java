package fr.movieapp.entities;

import fr.movieapp.models.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        schema = "public",
        name = "profile_entity",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"adult", "genre_list"})})
public class ProfileEntity {
    @Id
    @GeneratedValue
    private Long id;

    private boolean adult = false;

    @Enumerated(EnumType.STRING)
    private List<Genre> genreList = new ArrayList<>();
}
