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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt = null;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Float rating;

    @ManyToOne
    private ChargingStation chargingStation;

    @ManyToOne
    private User user;

}