package org.ponge.ratingapp.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String name;

    @Column(length = 4096)
    public String description;

    public static Product findByName(String name) {
        return find("name", name).firstResult();
    }
}
