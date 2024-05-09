package com.example.reciepemad1.Listeners;

import com.example.reciepemad1.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
