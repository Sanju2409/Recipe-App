package com.example.reciepemad1.Listeners;

import com.example.reciepemad1.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response,String message);
    void didError(String message);
}
