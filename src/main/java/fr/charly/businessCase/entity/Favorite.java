package fr.charly.businessCase.entity;

import fr.charly.businessCase.entity.embedded.UserChargingStationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Favorite {

    @EmbeddedId
   // @JsonView(JsonViews.FavoriteView.class)
    private UserChargingStationId id;

    @ManyToOne
    @JoinColumn(name = "user_uuid", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "charginStation_uuid", insertable = false, updatable = false)
    private ChargingStation chargingStation;

    @Column(nullable = false)
    private LocalDateTime createdAt;


}