package fr.charly.businessCase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViews.UserMinimalView.class)
    private String uuid;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String lastName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String firstName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String phone;

    private LocalDate birthedAt;

    private String activationCode;

    private LocalDateTime activationCodeSentAt;

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


    @JsonView(JsonViews.UserMinimalView.class)
    public boolean isVerified() {
        return activationCode == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        JSONArray roles = new JSONArray(this.roles);
//        roles.forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.toString()));
//        })
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (this.role.contains("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}