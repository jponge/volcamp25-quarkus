package org.ponge.ratingapp.endpoints;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ponge.ratingapp.data.Product;
import org.ponge.ratingapp.data.Review;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewsResource {

    @GET
    @Path("/of/{id}")
    public List<Review> allForProduct(Long id) {
        return Review.findByProductId(id);
    }

    record NewReview(long productId,
                     @Min(value = 0, message = "Rating must be at least 0")
                     @Max(value = 5, message = "Rating must be at most 5") int rating,
                     String comment) {
    }

    @POST
    @Transactional
    public Response create(@Valid NewReview newReview) {

        Product product = Product.findById(newReview.productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        Review review = new Review();
        review.product = product;
        review.rating = newReview.rating;
        review.comment = newReview.comment;
        Review.persist(review);

        return Response
                .status(Response.Status.CREATED)
                .entity(review)
                .build();
    }

    record Average(long id, double rating) {}

    @GET
    @Path("/average/{id}")
    public Average average(Long id) {
        try {
            return new Average(id, Review.averageRating(id));
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Product not found");
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Review entity = Review.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}
