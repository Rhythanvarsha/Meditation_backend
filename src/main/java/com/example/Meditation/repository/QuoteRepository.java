package com.example.Meditation.repository;


import com.example.Meditation.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {
}
