package com.example.Meditation.controller;

import com.example.Meditation.models.MeditationSession;
import com.example.Meditation.service.MeditationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/sessions")
public class SessionController {


    @Autowired
    MeditationSessionService meditationSessionService;


    @GetMapping
    public List<MeditationSession> getAllSessions(){
        return meditationSessionService.getAllSessions();
    }



    @GetMapping("/filter/category")
    public List<MeditationSession>  filterByCategory(@RequestParam String category){
        return meditationSessionService.filterByCategory(category);
    }

    @GetMapping("/filter/duration")
    List<MeditationSession> filterByDuration(@RequestParam int maxMinutes){
        return meditationSessionService.filterByDuration(maxMinutes);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MeditationSession>  create(@RequestBody MeditationSession session){
        return ResponseEntity.ok(meditationSessionService.addSession(session));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MeditationSession> update(@PathVariable Long id,@RequestBody MeditationSession updated){
        return ResponseEntity.ok(meditationSessionService.updateSession(id,updated));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        meditationSessionService.deleteSession(id);
        return ResponseEntity.ok().build();
    }

}
