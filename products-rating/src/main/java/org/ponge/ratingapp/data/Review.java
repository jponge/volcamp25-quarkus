package org.ponge.ratingapp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Review extends PanacheEntity {

    @ManyToOne(optional = false)
    @JsonIgnore
    public Product product;

    @Column(nullable = false)
    public int rating;

    @Column(length = 4096, nullable = false)
    public String comment;

    public static List<Review> findByProductId(Long productId) {
        return find("product.id", productId).list();
    }

    public static Double averageRating(Long productId) {
        return findByProductId(productId).stream()
                .mapToDouble(r -> r.rating)
                .average().orElseThrow();
    }
}
