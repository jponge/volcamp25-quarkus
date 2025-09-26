package org.ponge.midastouch.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import org.ponge.midastouch.data.Product;
import org.ponge.midastouch.data.Review;

import java.util.List;

@RegisterAiService
@SystemMessage("""
        You expertise is summarizing products reviews.
        
        Input:
        You are given the product name.
        You are given a Markdown-formatted text list where each item is a review for the product.
        
        Output:
        A single paragraph in plain text, no formatting, no Markdown.
        Highlight the pros and cons from the reviews in a balanced fashion.
        """)
public interface SummaryService {

    @UserMessage("""
            Product name: {product.name()}
            
            Reviews:
            {#for review in reviews}
            - {review.comment()}
            {/for}
            """)
    String summarize(Product product, List<Review> reviews);

    @UserMessage("""
            Product name: {product.name()}
            
            Reviews:
            {#for review in reviews}
            - {review.comment()}
            {/for}
            
            Add to the output paragraph a sentence with the average rating for the product with id={product.id()}.
            You must obtain the rating (a double value) from a tool that provides the average rating for a product.
            Do not invent a rating: if you cannot get a rating, say that you could not get one.
            The ratings vary from 0 (worse) to 5 (best).
            Do not add a title with the product name in the output, it must remain a single plain text paragraph.
            """)
    @ToolBox(MidasToolbox.class)
    String summarizeWithAverage(Product product, List<Review> reviews);
}
