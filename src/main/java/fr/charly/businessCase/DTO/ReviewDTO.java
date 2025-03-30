package fr.charly.businessCase.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {


    @NotBlank
    private String content;

    @NotNull
    private Float rating;

//    @NotBlank
//    private String chargingStationId;
//
//    private Long userId;

}
