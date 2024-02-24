package edu.hogwarts.studentadmin.models.DTOs;

import edu.hogwarts.studentadmin.models.EmploymentType;

import java.time.LocalDate;

public class TeacherPatchDTO {
    private Boolean headOfHouse;
    private LocalDate employmentEnd;
    private EmploymentType employmentType;

    public Boolean getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(Boolean headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }
}
