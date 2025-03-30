package fr.charly.businessCase.DTO;


import fr.charly.businessCase.entity.Localisation;
import fr.charly.businessCase.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLocalisationDTO {

//    @NotBlank
    private Long localisationId;

    @NotBlank
    private Boolean isBilling;

    private User ownerId;

}
