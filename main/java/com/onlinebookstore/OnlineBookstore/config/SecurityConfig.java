package com.onlinebookstore.OnlineBookstore.config;

import com.onlinebookstore.OnlineBookstore.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public pages
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/catalog.html",
                                "/cart.html",
                                "/login.html",
                                "/register.html",
                                "/css/**",
                                "/js/**",
                                "/script/**",
                                "/images/**",
                                "/api/books/**",
                                "/api/categories/**"
                        ).permitAll()

                        // Protect these
                        .requestMatchers("/api/cart/**", "/api/orders/**", "/orders.html").authenticated()

                        // Anything else — allow all (optional)
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .defaultSuccessUrl("/index.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index.html")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
