package com.vendora.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @NotNull(message = "Category id cannot be null,please provide a id ")
    private Integer category_id;
    @NotNull(message = "Name of a category cannot be null")
    @Size(max = 40, message = "Category name must be at most 40 characters")
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
