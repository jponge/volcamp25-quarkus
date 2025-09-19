package org.ponge.ratingapp.eventing;

import org.ponge.ratingapp.data.Product;
import org.ponge.ratingapp.data.Review;

public record ReviewPostedEvent(
        long reviewId,
        long productId,
        String productName,
        String productDescription,
        int rating,
        String comment) {

    public static ReviewPostedEvent of(Product product, Review review) {
        return new ReviewPostedEvent(review.id, product.id, product.name, product.description, review.rating, review.comment);
    }
}
