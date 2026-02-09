package ua.duikt.learning.java.pro.spring.individualseventhsprint.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO: Configure authorizeHttpRequests
        // 1. /public/** accessible to everyone (permitAll)
        // 2. /admin/** accessible only to ADMIN role (hasRole)
        // 3. All other requests require authentication
        // 4. Enable httpBasic (Customizer.withDefaults())

        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Change this!
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // TODO: Create two users:
        // "user" with password "password" and role "USER"
        // "admin" with password "admin" and role "ADMIN"

        // Hint: use User.withDefaultPasswordEncoder() (for learning purposes only!)
        // or "{noop}password"

        return new InMemoryUserDetailsManager(
                // ... users here
        );
    }
}
