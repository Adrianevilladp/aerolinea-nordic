package com.aerovik.nordic.config;

import com.aerovik.nordic.security.JwtAuthenticationEntryPoint;
import com.aerovik.nordic.security.JwtAuthenticationFilter;
import com.aerovik.nordic.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig {

    public static final String BOOKING_ENDPOINT = "/api/v1/booking/**";
    public static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    public static final String ENDPOINT_SIGN_UP = "/api/v1/auth/register";
    public static final String ENDPOINT_SEARCH_FLIGHT = "/api/v1/search/**";
    public static final String ENDPOINT_CUSTOMER = "/api/v1/customer/**";

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    try {
                        auth
                                .requestMatchers(ENDPOINT_CUSTOMER)
                                .hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(LOGIN_ENDPOINT, ENDPOINT_SIGN_UP,
                                        ENDPOINT_SEARCH_FLIGHT, "/api/v1/booking/create/single")
                                .permitAll()
                                .anyRequest()
                                .authenticated();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .formLogin(form -> {
                    try {
                        form
                                .loginPage("/api/v1/auth/login")//it's a html page, not that
                                //.usernameParameter("username")
                                //.passwordParameter("password")
                                .defaultSuccessUrl("/api/v1/flight/search/all")
                                .and()
                                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }




}
