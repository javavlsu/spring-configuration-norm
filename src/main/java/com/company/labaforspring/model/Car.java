package com.company.labaforspring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")//то же что и @Entity("Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private Date date;

    public Car(String name, String description) {
        this.name = name;
        this.description = description;
        this.date = new Date();
    }

    public Car(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Car(int id, String name, String description, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Car() {
    }
}
