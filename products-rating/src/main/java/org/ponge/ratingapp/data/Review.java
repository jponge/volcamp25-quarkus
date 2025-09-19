package org.ponge.ratingapp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

import java.util.List;

@Entity
@NamedQuery(
        name="AverageRating",
        query = "select avg(r.rating) from Review r where r.product.id = ?1"
)
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
        Double avg = find("#AverageRating", productId)
                .project(Double.class)
                .firstResult();
        return (avg != null) ? avg : -1.0d;
    }
}
