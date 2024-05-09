package com.example.reciepemad1.Listeners;

import com.example.reciepemad1.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);

}
