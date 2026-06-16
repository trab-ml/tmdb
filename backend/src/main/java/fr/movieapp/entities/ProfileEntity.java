package fr.movieapp.entities;

import fr.movieapp.models.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "profile_entity", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "adult", "genre_list" }) })
@Builder
public class ProfileEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private boolean adult = false;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private List<Genre> genreList = new ArrayList<>();
}
