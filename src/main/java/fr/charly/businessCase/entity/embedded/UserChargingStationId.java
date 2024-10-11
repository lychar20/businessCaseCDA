package fr.charly.businessCase.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChargingStationId implements Serializable {

    @Column(name = "chargingStation_uuid")
    private String chargingStationUuid;

    @Column(name = "user_uuid")
    private String userUuid;

}
