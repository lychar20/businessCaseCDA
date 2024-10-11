package fr.charly.businessCase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String phone;

    private LocalDate birthedAt;

    private String activationCode;


    private LocalDateTime createdAt;

    private String role;


    @OneToMany(mappedBy = "userFrom")
    private List<UserReview> userReviewsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "userTo")
    private List<UserReview> userReviewsTo = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<UserLocalisation> userLocalisations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();



    public boolean isVerified() {
        return true;
    }

}