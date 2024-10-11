package fr.charly.businessCase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accessDirectives;

    private Boolean onFoot;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = null;

    @ManyToOne
    private Power power;

    @ManyToOne
    private Localisation localisation;

    @OneToMany(mappedBy = "chargingStation")
    private List<HourlyRate> hourlyRates = new ArrayList<>();

    @OneToMany(mappedBy = "chargingStation")
    private List<Media> medias = new ArrayList<>();

    @OneToMany(mappedBy = "chargingStation")
    private List<Review> reviews;

    @OneToMany(mappedBy = "chargingStation")
    private List<Booking> bookings = new ArrayList<>();




}