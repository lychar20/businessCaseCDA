package fr.charly.businessCase.DTO;


import fr.charly.businessCase.entity.UserLocalisation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {

    @DateTimeFormat
    private LocalDateTime startedAt;

    @DateTimeFormat
    private LocalDateTime finishedAt;

  //  private Long userLocalisationId;

}
