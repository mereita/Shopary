package com.example.mayjunejuly.myapplication;

/**
 * Created by MAYJUNEJULY on 1/10/2018.
 */

public class Product {
    private String id;
    private String name;
    private String description;
    private Integer stock;
    private double price;
    private String image;

    public Product(String id, String name, double price, Integer stock, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
