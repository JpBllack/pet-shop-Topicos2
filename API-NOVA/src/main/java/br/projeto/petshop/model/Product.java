package br.projeto.petshop.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Product extends DefaultEntity{
    private String name;
    private Double price;
    private Integer inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
