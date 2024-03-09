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

    public Product(int id, String name, int category_id, String desc, int inventory_id, float price) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.desc = desc;
        this.inventory_id = inventory_id;
        this.price = price;
    }
}