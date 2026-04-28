package vn.aitest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.aitest.item.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String AI_MODELS_ALL = "/ai-models/**";
    private static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/actuator/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/ai-models")
                        .hasAnyRole("USER", "PUBLISHER", ROLE_ADMIN)
                        .requestMatchers(org.springframework.http.HttpMethod.GET, AI_MODELS_ALL)
                        .hasRole(ROLE_ADMIN)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/ai-models")
                        .hasAnyRole("PUBLISHER", ROLE_ADMIN)
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, AI_MODELS_ALL)
                        .hasRole(ROLE_ADMIN)
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, AI_MODELS_ALL)
                        .hasRole(ROLE_ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}