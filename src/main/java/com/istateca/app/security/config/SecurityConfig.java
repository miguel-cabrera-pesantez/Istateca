package com.istateca.app.security.config;

import com.istateca.app.security.filter.CsrfCookieFilter;
import com.istateca.app.security.filter.JWTTokenGeneratorFilter;
import com.istateca.app.security.filter.JWTTokenValidatorFilter;
import com.istateca.app.security.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class SecurityConfig {

    @Value("${data_folder}")
    private String rutageneral;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("*"));// Reemplaza "http://example.com" con tu dominio permitido o patrón
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                    .requestMatchers("/autor/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/autorlibro/**").hasAnyRole("STUD", "BLIB", "ADMIN")
                    .requestMatchers("/bibliotecariocargo/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/carrera/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/etiqueta/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/tags/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/libro/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/persona/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/prestamo/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/tercero/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/tipo/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/donante/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/sugerencia/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/terceroprestamo/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/carrerafenix/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/usuariofenix/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/notificacion/**").hasAnyRole("STUD", "BLIB", "ADMIN", "DOCEN")
                    .requestMatchers("/ingresar").authenticated()
                    // Letting Access of fenix to ALL by the moment
                    .requestMatchers("/ingresar", "/credentials", rutageneral,"/libro/listar").permitAll()
                .and().formLogin().and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}