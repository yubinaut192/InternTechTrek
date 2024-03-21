package com.example.TechTrekBackend.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int category_id;
    @Column(name = "`desc`")
    private String desc;
    private int inventory_id;
    private float price;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImageURL(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product(int id, String name, int category_id, String desc, int inventory_id, float price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.desc = desc;
        this.inventory_id = inventory_id;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}