package com.example.TechTrekBackend.Product;

public class Product {
    private Long id;
    private String name;
    private int category_id;
    private String desc;
    private int inventory_id;
    private float price;

    public Product(Long id, String name, int category_id, String desc, int inventory_id, float price) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.desc = desc;
        this.inventory_id = inventory_id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                ", desc='" + desc + '\'' +
                ", inventory_id=" + inventory_id +
                ", price=" + price +
                '}';
    }
}