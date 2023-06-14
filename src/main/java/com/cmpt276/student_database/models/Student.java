package com.cmpt276.student_database.models;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sId;
    private String name;
    private float weight;
    private float height;
    private String hairColor;
    private float gpa;
    public Student(){
        
    }
    public Student(String name, float weight, float height, String hairColor, float gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.gpa = gpa;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
    public String getName() {
        return name;
    }
    public float getWeight() {
        return weight;
    }
    public float getHeight() {
        return height;
    }
    public String getHairColor() {
        return hairColor;
    }
    public float getGpa() {
        return gpa;
    }
    public int getsId() {
        return sId;
    }
    public void setsId(int sId) {
        this.sId = sId;
    }
}
