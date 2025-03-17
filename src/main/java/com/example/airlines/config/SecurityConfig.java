package com.example.airlines.config;

import com.example.airlines.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/public/**", "/register", "/login", "/h2-console/**", "/images/*").permitAll() // Zezwól na dostęp do strony głównej, rejestracji, logowania i konsoli H2 bez logowania
                        .requestMatchers("/flights", "/tickets").authenticated() // Wymagaj logowania dla tych endpointów
                        .anyRequest().authenticated() // Wszystkie inne żądania wymagają logowania
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Przekieruj po udanym logowaniu
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Endpoint do wylogowania
                        .logoutSuccessUrl("/?logout") // Przekieruj na stronę główną po wylogowaniu
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Wyłącz CSRF dla konsoli H2
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .disable() // Wyłącz X-Frame-Options dla konsoli H2
                        )
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}