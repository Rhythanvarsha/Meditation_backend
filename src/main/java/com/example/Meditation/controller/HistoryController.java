package com.example.Meditation.controller;





import com.example.Meditation.models.MeditationHistory;
import com.example.Meditation.service.MeditationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private MeditationHistoryService meditationHistoryService;

    @PostMapping("/complete/{sessionId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MeditationHistory> completeSession(
            @PathVariable Long sessionId,
            Authentication authentication) {
        return ResponseEntity.ok(
                meditationHistoryService.saveSession(authentication.getName(), sessionId)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MeditationHistory>> getUserHistory(Authentication authentication) {
        return ResponseEntity.ok(
                meditationHistoryService.getUserHistory(authentication.getName())
        );
    }


    @GetMapping("/all")
    public List<MeditationHistory> getAllMediationHistory(){
        return meditationHistoryService.getService();
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Long> getCompletedCount(Authentication authentication) {
        return ResponseEntity.ok(
                meditationHistoryService.getCompletedCount(authentication.getName())
        );
    }
}


/*Step-by-Step to test /api/history/complete/{sessionId}
Login first via /api/auth/login with correct credentials:

Body: {"username": "user1", "password": "yourpassword"}

Get the JWT token from the response.

Call your POST endpoint with token:

URL: POST http://localhost:8080/api/history/complete/1

Authorization:

Type: Bearer Token

Token: Paste your JWT token here

Headers:

Content-Type: application/json

Body: Leave it empty (since you're just marking session complete by ID)

Make sure the sessionId 1 exists in your MeditationSession table or entity.

*/


