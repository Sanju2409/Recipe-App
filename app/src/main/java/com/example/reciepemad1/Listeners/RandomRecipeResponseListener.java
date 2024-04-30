package com.example.reciepemad1.Listeners;

import com.example.reciepemad1.Models.RandomReciepeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomReciepeApiResponse response,String message);
    void didError(String message);
}
