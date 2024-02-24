package edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.hogwarts.studentadmin.models.DTOs.StudentPatchDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String allName;
    @ManyToOne
    @JoinColumn(name = "house")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private House house;

    private LocalDate dateOfBirth;
    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;
    private int schoolYear;

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, boolean prefect,
                   int enrollmentYear, Integer graduationYear, boolean graduated, House house, int schoolYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
        this.house = house;
        this.schoolYear = schoolYear;
        this.allName = (firstName + " " + (middleName.isEmpty() ? "" : middleName + " ") + lastName + " ").trim();
    }

    public Student(String fullName, LocalDate dateOfBirth, boolean prefect,
                   int enrollmentYear, int graduationYear, boolean graduated, House house, int schoolYear) {
        List<String> nameParts = List.of(fullName.split("\\s"));

        this.firstName = getFirstNameFromFullName(nameParts);
        this.middleName = getMiddleNameFromFullName(nameParts);
        this.lastName = getLastNameFromFullName(nameParts);
        this.dateOfBirth = dateOfBirth;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
        this.house = house;
        this.schoolYear = schoolYear;
        this.allName = (firstName + " " + (middleName.isEmpty() ? "" : middleName + " ") + lastName + " ").trim();
    }

    public Student() {
    }

    public Student(Student otherStudent) {
        this.firstName = otherStudent.getFirstName();
        this.middleName = otherStudent.getMiddleName();
        this.lastName = otherStudent.getLastName();
        this.dateOfBirth = otherStudent.getDateOfBirth();
        this.prefect = otherStudent.isPrefect();
        this.enrollmentYear = otherStudent.getEnrollmentYear();
        this.graduationYear = otherStudent.getGraduationYear();
        this.graduated = otherStudent.isGraduated();
        this.house = otherStudent.getHouse();
        this.schoolYear = otherStudent.getSchoolYear();
    }

    public void copyFrom(Student otherStudent) {
        this.setFirstName(otherStudent.getFirstName());
        this.setMiddleName(otherStudent.getMiddleName());
        this.setLastName(otherStudent.getLastName());
        this.setDateOfBirth(otherStudent.getDateOfBirth());
        this.setPrefect(otherStudent.isPrefect());
        this.setEnrollmentYear(otherStudent.getEnrollmentYear());
        this.setGraduationYear(otherStudent.getGraduationYear());
        this.setGraduated(otherStudent.isGraduated());
        this.setHouse(otherStudent.getHouse());
    }

    public void applyPatch(StudentPatchDTO patchDTO) {
        if (patchDTO.getPrefect() != null) {
            this.setPrefect(patchDTO.getPrefect());
        }
        if (patchDTO.getSchoolYear() != 0) {
            this.setSchoolYear(patchDTO.getSchoolYear());
        }
        if (patchDTO.getGraduated() != null) {
            this.setGraduated(patchDTO.getGraduated());
        }
        if (patchDTO.getGraduationYear() != 0) {
            this.setGraduationYear(patchDTO.getGraduationYear());
        }

        // Check if graduationYear is not null and greater than 0
        if (patchDTO.getGraduationYear() > 0) {
            this.setGraduated(true);
        }
    }


    public String getFirstNameFromFullName(List<String> nameParts) {
         String firstNameLowerCased = nameParts.get(0).toLowerCase();
         String firstLetterCapitalized = firstNameLowerCased.substring(0, 1).toUpperCase();

         return firstLetterCapitalized + firstNameLowerCased.substring(1);
    }

    public String getMiddleNameFromFullName(List<String> nameParts) {
        String result = "";

        if (nameParts.size() > 2) {
            List<String> middleNames = nameParts.subList(1, nameParts.size() - 1);
            for (int i = 0; i < middleNames.size(); i++) {
                String middleNameLowerCased = middleNames.get(i).toLowerCase();
                String capitalizedMiddleName = middleNameLowerCased.substring(0, 1).toUpperCase() +
                        middleNameLowerCased.substring(1);

                result += capitalizedMiddleName + " ";
            }
            return result.trim();
        } else {
            return "";
        }
    }

    public String getLastNameFromFullName(List<String> nameParts) {
        String lastNameLowerCased = nameParts.get(nameParts.size() - 1).toLowerCase();

        return lastNameLowerCased.substring(0,1).toUpperCase() + lastNameLowerCased.substring(1);
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    @Override
    public String toString() {
        return firstName + middleName + lastName + " ";
    }
}
