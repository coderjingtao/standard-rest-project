package com.joseph.standardwebproject.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class BasicSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //all requests should be authenticated
        http.authorizeHttpRequests(
                auth -> {
                    auth
                            //.requestMatchers("/users").hasAnyRole("USER","ADMIN")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/static/favicon.ico").permitAll()
                            .anyRequest().authenticated();
                }
        );
        //session
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // without form login, if a request is not authenticated, a pop window page is shown
        //http.formLogin();
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF protection
        http.csrf().disable();

        // allow frame under same origin
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder){
        UserDetails user = User.withUsername("joseph")
                //.password("{noop}123456")
                .password("123456")
                .passwordEncoder(password -> passwordEncoder.encode(password))
                .authorities("read")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                //.password("{noop}123456")
                .password("123456")
                .passwordEncoder(password -> passwordEncoder.encode(password))
                .authorities("read")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
