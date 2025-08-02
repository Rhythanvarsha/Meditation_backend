package com.example.Meditation.service;

import com.example.Meditation.models.Quote;
import org.springframework.stereotype.Service;



import com.example.Meditation.models.Quote;
import com.example.Meditation.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    private final Random random = new Random();

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();

        return quotes.get(random.nextInt(quotes.size()));
    }

    public List<Quote> getAllQuote() {
        return quoteRepository.findAll();
    }



    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }


    public String postService(Quote q) {
        quoteRepository.save(q);
        return "ADDED";
    }
}

