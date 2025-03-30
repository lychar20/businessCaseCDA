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
public class Power {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PowerMinimalView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.PowerMinimalView.class)
    private Float value;

}