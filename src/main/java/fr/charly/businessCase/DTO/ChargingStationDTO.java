package fr.charly.businessCase.DTO;


import fr.charly.businessCase.entity.HourlyRate;
import fr.charly.businessCase.entity.Localisation;
import fr.charly.businessCase.entity.Media;
import fr.charly.businessCase.entity.Power;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChargingStationDTO {

    @NotBlank
    private String name;

    @NotNull
    private LocalisationDTO localisation;

    @NotBlank
    private String accessDirectives;


    private Boolean onFoot;

    @NotNull
    private Long powerId;


 //   private HourlyRateDTO hourlyRate;

//    @NotBlank
//    private List<Media> medias = new ArrayList<>();

    @NotNull
    private List<HourlyRateDTO> hourlyRates = new ArrayList<>();

}
