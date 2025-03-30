package fr.charly.businessCase.entity;
import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class HourlyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    private Integer value;

    @Column(nullable = false)
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    private Float minimumDuration;

    @ManyToOne
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    private  ChargingStation chargingStation;



}