package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.DTOs.StudentPatchDTO;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable int id, @RequestBody StudentPatchDTO patchDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.applyPatch(patchDTO);
            studentRepository.save(existingStudent);
            return ResponseEntity.ok().body(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/name/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String name) {
        Optional<Student> student = studentRepository.findFirstByAllNameContainingIgnoreCase(name);
        System.out.println(name);
        return ResponseEntity.of(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);

        return ResponseEntity.of(student);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> original = studentRepository.findById(id);

        if (original.isPresent()) {
            Student originalStudent = original.get();
            originalStudent.copyFrom(student);

            Student updatedStudent = studentRepository.save(originalStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            Student newStudent = new Student();
            newStudent.copyFrom(student);

            Student savedStudent = studentRepository.save(newStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        studentRepository.deleteById(id);

        return ResponseEntity.of(studentToDelete);
    }


}
