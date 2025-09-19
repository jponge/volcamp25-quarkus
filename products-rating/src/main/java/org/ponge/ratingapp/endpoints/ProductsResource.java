package org.ponge.ratingapp.endpoints;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ponge.ratingapp.data.Product;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsResource {

    @GET
    public List<Product> all() {
        return Product.listAll();
    }

    @GET
    @Path("/{id}")
    public Product get(Long id) {
        Product entity = Product.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Product product) {
        Product.persist(product);
        return Response
                .status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Product update(Long id, Product updated) {
        Product entity = Product.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.name = updated.name;
        entity.description = updated.description;
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Product entity = Product.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @GET
    @Path("/count")
    public long count() {
        return Product.count();
    }

    @GET
    @Path("/find/{name}")
    public Product findByName(String name) {
        return Product.findByName(name);
    }
}
