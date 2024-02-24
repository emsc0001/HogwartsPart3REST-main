package edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;

    public Course() {
    }

    public Course(Teacher teacher, String subject, int schoolYear, boolean current, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.students = students;
        this.teacher = teacher;
    }

    public Course(Course otherCourse) {
        this.id = otherCourse.getId();
        this.subject = otherCourse.getSubject();
        this.schoolYear = otherCourse.getSchoolYear();
        this.current = otherCourse.isCurrent();
        this.students = otherCourse.getStudents();
        this.teacher = otherCourse.getTeacher();
    }

    public void copyFrom(Course otherCourse) {
        this.setSubject(otherCourse.getSubject());
        this.setSchoolYear(otherCourse.getSchoolYear());
        this.setCurrent(otherCourse.isCurrent());
        this.setStudents(otherCourse.getStudents());
        this.setTeacher(otherCourse.getTeacher());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setStudentToCourse(Student student) {
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
