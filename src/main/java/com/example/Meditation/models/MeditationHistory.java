package com.example.Meditation.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MeditationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate dateCompleted;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private MeditationSession session;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "session_id", referencedColumnName = "empId")
//    private RegisterDetails register;
}
