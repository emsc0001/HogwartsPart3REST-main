package edu.hogwarts.studentadmin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HouseColor {
    @Id
    private String color;

    public HouseColor() {
    }

    public HouseColor(String color) {
        this.color = color;
    }

    public HouseColor(HouseColor otherHouseColor) {
        this.color = otherHouseColor.getColor();
    }

    public void copyFrom(HouseColor otherHouseColor) {
        this.setColor(otherHouseColor.getColor());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

