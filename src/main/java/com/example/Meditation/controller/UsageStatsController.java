package com.example.Meditation.controller;



import com.example.Meditation.service.UsageStatsService;
import com.example.Meditation.stats.UsageStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stats")
public class UsageStatsController {

    @Autowired
    private UsageStatsService usageStatsService;

    // Only ADMINs are allowed to view usage stats
    @GetMapping("/usage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsageStatsResponse> getUsageStats() {
        UsageStatsResponse stats = usageStatsService.getUsageStats();
        return ResponseEntity.ok(stats);
    }
}
