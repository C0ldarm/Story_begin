package vn.aitest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.aitest.item.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/ai-models").hasAnyRole("USER", "PUBLISHER", "ADMIN")           // GET all
                        .requestMatchers("POST", "/ai-models").hasAnyRole("PUBLISHER", "ADMIN")          // POST
                        .requestMatchers("PUT", "/ai-models/**").hasRole("ADMIN")                       // PUT
                        .requestMatchers("DELETE", "/ai-models/**").hasRole("ADMIN")                    // DELETE
                        .requestMatchers("/ai-models/**").hasAnyRole("USER", "PUBLISHER", "ADMIN")      // GET one

                        // .requestMatchers("/ai-models").hasAnyRole("USER", "PUBLISHER", "ADMIN")
                        // .requestMatchers("/ai-models/**").hasRole("ADMIN")
                        // .anyRequest().authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .userDetailsService(userService);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}