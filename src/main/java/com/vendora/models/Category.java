package com.vendora.models;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Integer category_id;
    private String name;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
