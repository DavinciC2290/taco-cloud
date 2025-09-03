package com.example.saul.taco_cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.example.saul.taco_cloud.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository)
    {
        return username -> userRepository.findByUsername(username)
                .map(u -> User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    } 
    
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/login", "/images/**").permitAll()
                .requestMatchers("/design", "/orders/**").authenticated()
                .anyRequest().authenticated()

            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .permitAll()

            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
            );

        return http.build();

    }

}
