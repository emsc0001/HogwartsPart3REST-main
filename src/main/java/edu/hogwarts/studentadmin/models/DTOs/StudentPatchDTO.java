package edu.hogwarts.studentadmin.models.DTOs;

public class StudentPatchDTO {
    private Boolean prefect;
    private int schoolYear;
    private Boolean graduated;
    private int graduationYear;

    public Boolean getPrefect() {
        return prefect;
    }

    public void setPrefect(Boolean prefect) {
        this.prefect = prefect;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
