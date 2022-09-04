package com.epam.onlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    public long id;

    @NotBlank
    public String name;

    @NotBlank
    public String description;

    @NotBlank
    public double price;

    @NotBlank
    public int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                Objects.equals(description, product.description) &&
                price == product.price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (int) price;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}