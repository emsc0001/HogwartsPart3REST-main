package kea.exercise.studentadmin.repositories;

import kea.exercise.studentadmin.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findFirstByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
