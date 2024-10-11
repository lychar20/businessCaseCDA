package fr.charly.businessCase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;


    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private String status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ChargingStation chargingStation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserLocalisation userLocalisation;



}