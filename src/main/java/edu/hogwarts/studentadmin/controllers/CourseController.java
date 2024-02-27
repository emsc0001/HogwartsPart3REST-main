package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Course;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponseDTO> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CourseResponseDTO>> getCourseById(@PathVariable Long id) {
        Optional<CourseResponseDTO> course = courseService.findById(id);
        if (course.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }
    @GetMapping("/{id}/teacher")
    public ResponseEntity<Optional<TeacherResponseDTO>> getTeacherByCourseId(@PathVariable Long id) {
        Optional<TeacherResponseDTO> teacher = courseService.findTeacherByCourseId(id);
        if (teacher.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Optional<List<StudentResponseDTO>>> getStudentsByCourseId(@PathVariable Long id) {
        Optional<List<StudentResponseDTO>> students = courseService.findStudentsByCourseId(id);
        if (students.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public CourseResponseDTO createCourse(@RequestBody CourseRequestDTO course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO course) {
        return ResponseEntity.of(courseService.updateIfExists(id, course));
    }

    @PatchMapping("/{id}/teacher")
    public ResponseEntity<CourseResponseDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTOId teacherId) {
        return ResponseEntity.of(courseService.updateTeacher(id, teacherId));
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<CourseResponseDTO> addStudentsToCourse(@PathVariable Long id, @RequestBody StudentDTOIds studentIds) {
        return ResponseEntity.of(courseService.addStudentsToCourse(id, studentIds));
    }

    @PostMapping("/{id}/students/names")
    public ResponseEntity<CourseResponseDTO> addStudentsToCourseByNames(@PathVariable Long id, @RequestBody StudentDTONames studentNames) {
        return ResponseEntity.of(courseService.addStudentsToCourseByNames(id, studentNames));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.of(courseService.deleteById(id));
    }

    @DeleteMapping("/{id}/teacher")
    public ResponseEntity<CourseResponseDTO> removeTeacherFromCourse(@PathVariable Long id) {
        return ResponseEntity.of(courseService.removeTeacherFromCourse(id));
    }

    @DeleteMapping("/{id}/students")
    public ResponseEntity<CourseResponseDTO> removeStudentsFromCourse(@PathVariable Long id) {
        return ResponseEntity.of(courseService.removeStudentsFromCourse(id));
    }

}
