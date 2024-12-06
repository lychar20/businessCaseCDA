package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.UserDTO;
import fr.charly.businessCase.DTO.UserLoginDTO;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.response.JwtResponse;
import fr.charly.businessCase.security.JwtAuthenticatorService;
import fr.charly.businessCase.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private final UserService userService;
    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/auth/register")
    @JsonView(JsonViews.UserShowView.class)
    public User register(@Valid @RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDTO user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}
