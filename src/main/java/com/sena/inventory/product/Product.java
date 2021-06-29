package com.sena.inventory.product;

import com.sena.inventory.brand.Brand;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @DecimalMin(value = "1.0")
    private double price;
    @DecimalMin(value = "1.0")
    private double cost;
    //@NotNull(message = "debe seleccionar una marca")
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;
    @Column(columnDefinition = "boolean default true")
    private Boolean state;

    public Product(Integer id, String name, double price, double cost, Brand brand, Boolean state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.brand = brand;
        this.state = state;
    }

    public Product() {

    }

    public Product(String name, double price, double cost, Brand brand,Boolean state) {
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.brand = brand;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", brand=" + brand +
                ", state=" + state +
                '}';
    }
}
