package kea.exercise.studentadmin.repositories;

import kea.exercise.studentadmin.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Integer> {
    Optional<House> findByNameContainingIgnoreCase(String name);
}
