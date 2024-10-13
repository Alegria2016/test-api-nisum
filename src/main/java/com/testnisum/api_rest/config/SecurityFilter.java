package com.testnisum.api_rest.config;

import com.testnisum.api_rest.models.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/h2-test-api/**").permitAll();

                    authConfig.requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").permitAll();

                    authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}").hasAuthority(Permission.UPDATE_USER.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAuthority(Permission.DELETE_USER.name());

                    authConfig.anyRequest().denyAll();


                });

        return http.build();

    }


}
