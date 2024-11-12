//package com.project.Rakshak.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//
//    // Constructor injection for UserDetailsService
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    // HttpSecurity Configuration - Spring Security 6.x
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Authorization
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/", "/home", "/home/signup","/static/**").permitAll()  // Allow unauthenticated access to these paths
//                        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
//                        .requestMatchers("/profile/**").authenticated()  // Require authentication for all profile-related paths
//                        .anyRequest().authenticated()  // Any other request needs authentication
//                )
//                // Form Login
//                .formLogin(form -> form
//                        .loginPage("/home/login") // Custom login page
//                        .defaultSuccessUrl("/profile", true) // Redirect to the profile after login
//                        .permitAll() // Allow unauthenticated access to the login page
//                )
//                // Logout Configuration
//                .logout(logout -> logout
//                        .permitAll() // Allow unauthenticated access to logout
//                )
////                 Session Management
//                .sessionManagement(session -> session
//                        .maximumSessions(1) // Only one session per user
//                        .expiredUrl("/home/login?expired=true") // Redirect to login if session expires
//                );
//
//        return http.build();  // Return the configured SecurityFilterChain object
//    }
//
//    // Authentication Provider Bean
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }
//
//    // Password Encoder Bean (BCrypt)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);  // Configure BCryptEncoder with strength of 10
//    }
//}
