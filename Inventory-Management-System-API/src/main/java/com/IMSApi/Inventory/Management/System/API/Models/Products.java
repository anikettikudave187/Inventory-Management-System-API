package com.IMSApi.Inventory.Management.System.API.Models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String description;
    int stock_quantity;
    int low_stock_threshold;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public int getLow_stock_threshold() {
        return low_stock_threshold;
    }

    public void setLow_stock_threshold(int low_stock_threshold) {
        this.low_stock_threshold = low_stock_threshold;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
}
