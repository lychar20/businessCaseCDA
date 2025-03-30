package fr.charly.businessCase.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MediaDTO {

    @NotBlank
    private String extension;

    @NotBlank
    private String name;

    @NotBlank
    private String ChargingStationId;



}
