package org.launchcode.healthylivingtracker.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int caloricValue;

    public Food () {}

    public Food (String name, int caloricValue) {
        this.name = name;
        this.caloricValue = caloricValue;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloricValue() {
        return caloricValue;
    }

    public void setCaloricValue(int caloricValue) {
        this.caloricValue = caloricValue;
    }

}
