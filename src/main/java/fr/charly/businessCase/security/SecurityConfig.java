package fr.charly.businessCase.security;

import fr.charly.businessCase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtTokenFilter jwtTokenFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // il faut rajouter sa dans la chaine de méthodes
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                                        AntPathRequestMatcher.antMatcher("/swagger-ui/**")
//                                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/listing/**"),
//                                        AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/user/activate/**")
                                ).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/api/user/**")
//                                        AntPathRequestMatcher.antMatcher("/api/listing/"), // For a real API, we don't need it, it just an example !
//                                        AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/favorite/**")
                                ).authenticated()
                                .requestMatchers( // Useless there, but example to secure all admin route
                                        AntPathRequestMatcher.antMatcher("/api/admin/**")
                                ).hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
