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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/actuator/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/ai-models")
                        .hasAnyRole("USER", "PUBLISHER", "ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/ai-models/**")
                        .hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/ai-models")
                        .hasAnyRole("PUBLISHER", "ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/ai-models/**")
                        .hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/ai-models/**")
                        .hasRole("ADMIN")
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