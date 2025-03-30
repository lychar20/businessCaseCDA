package fr.charly.businessCase.DTO;


import fr.charly.businessCase.entity.UserLocalisation;
import jakarta.validation.constraints.NotBlank;
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
public class LocalisationDTO {

    private Long id;

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String streetName;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    private List<UserLocalisationDTO> userLocalisations = new ArrayList<>();

}
