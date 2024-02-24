package edu.hogwarts.studentadmin.repositories;

import edu.hogwarts.studentadmin.models.HouseColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseColorRepository extends JpaRepository<HouseColor, Integer> {
}
