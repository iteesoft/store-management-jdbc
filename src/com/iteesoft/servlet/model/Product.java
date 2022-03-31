package com.iteesoft.servlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    protected int id;
    protected String name;
    protected String description;
    protected float price;
    protected int quantity;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name, String description, float price) {
        this(name, description, price);
        this.id = id;
    }

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, float price, int quantity) {
        this(name, description, price);
        this.quantity = quantity;
    }

    public Product(int id, String name, String description, float price, int quantity) {
        this(id, name, description, price);
        this.quantity = quantity;
    }

}

