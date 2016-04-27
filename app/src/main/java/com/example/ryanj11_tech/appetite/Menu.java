package com.example.ryanj11_tech.appetite;

/**
 * Created by Jace on 4/25/2016.
 */
public class Menu {

    private String name;
    private String description;
    private String price;

    public Menu(String name, String description, String price)
    {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
