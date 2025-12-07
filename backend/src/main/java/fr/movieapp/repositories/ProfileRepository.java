package fr.movieapp.repositories;

import fr.movieapp.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {}
