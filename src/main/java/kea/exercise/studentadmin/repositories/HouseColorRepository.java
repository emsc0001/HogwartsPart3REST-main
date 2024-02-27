package kea.exercise.studentadmin.repositories;

import kea.exercise.studentadmin.models.HouseColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseColorRepository extends JpaRepository<HouseColor, Integer> {
}
