package com.example.Meditation.controller;


import com.example.Meditation.models.Quote;
import com.example.Meditation.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;



    @GetMapping("/daily")
    public Quote getDailyQuote(){
        return quoteService.getRandomQuote();
    }

    @GetMapping
    public List<Quote> getAllQuote(){
        return quoteService.getAllQuote();
    }



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String postQuote(@RequestBody Quote q){
        return quoteService.postService(q);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
