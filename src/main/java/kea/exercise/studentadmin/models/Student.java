package kea.exercise.studentadmin.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private @ManyToOne(fetch = FetchType.EAGER) House house;
    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;
    private int schoolYear;

    public Student(Long id, String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, boolean prefect, int enrollmentYear, int graduationYear, boolean graduated, int schoolYear) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
        this.schoolYear = schoolYear;
    }

    public Student(String fullName) {
        setFullName(fullName);
        // Leave other attributes as null
        this.id = null;
        this.dateOfBirth = null;
        this.house = null;
        this.prefect = false;
        this.enrollmentYear = 0;
        this.graduationYear = 0;
        this.graduated = false;
        this.schoolYear = 0;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return hasMiddleName() ? this.firstName + " " + this.middleName + " " + this.lastName : this.firstName + " " + this.lastName;
    }

    public boolean hasMiddleName() {
        return this.middleName != null;
    }

    public void setFullName(String fullName) {
        if(fullName == null) return;
        String[] names = fullName.split(" ");
        if (names.length == 1) {
            firstName = names[0];
        } else if (names.length == 2) {
            firstName = names[0];
            lastName = names[1];
        } else {
            firstName = names[0];
            middleName = names[1];
            lastName = names[2];
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(boolean prefect) {
        this.prefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }
}
