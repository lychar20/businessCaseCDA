package fr.charly.businessCase.DTO;


import fr.charly.businessCase.entity.User;
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
public class ReviewUpdateDTO {

    @NotBlank
    private String content;

    @NotNull
    private Float rating;


}
