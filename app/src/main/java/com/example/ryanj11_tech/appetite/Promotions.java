package com.example.ryanj11_tech.appetite;

/**
 * Created by Jace on 4/23/2016.
 */
public class Promotions {

    private String name;
    private String description;
    private String points;

    public Promotions(String name, String description, String points)
    {
        this.setName(name);
        this.setDescription(description);
        this.setPoints(points);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
