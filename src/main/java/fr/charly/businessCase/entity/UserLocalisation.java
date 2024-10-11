package fr.charly.businessCase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class UserLocalisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isBilling;

    @OneToMany(mappedBy = "userLocalisation")
    private List<Booking> bookings;

    @ManyToOne
    private User user;

    @ManyToOne
    private Localisation localisation;

}