package fr.charly.businessCase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.json_views.JsonViews;
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
    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private String uuid;

    @Column(nullable = false)
    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accessDirectives;

    private Boolean onFoot;

    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = null;

    @ManyToOne
    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private Power power;

    @ManyToOne
    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private Localisation localisation;

    @OneToMany(mappedBy = "chargingStation")
    @JsonView(JsonViews.ChargingStationMinimalView.class)
    private List<HourlyRate> hourlyRates = new ArrayList<>();

    @OneToMany(mappedBy = "chargingStation")
    private List<Media> medias = new ArrayList<>();

    @OneToMany(mappedBy = "chargingStation")
    private List<Review> reviews;

    @OneToMany(mappedBy = "chargingStation")
    private List<Booking> bookings = new ArrayList<>();




}