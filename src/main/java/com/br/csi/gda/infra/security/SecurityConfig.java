package com.br.csi.gda.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AutenticacaoFilter filter;
    public SecurityConfig(AutenticacaoFilter filter){
        this.filter = filter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable()).sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                //permissões para usuários
                                .requestMatchers(HttpMethod.GET, "/usuario").permitAll()
                                //.requestMatchers(HttpMethod.POST, "/usuario").hasAuthority("ROLE_ADMIN")
                                //permissão liberada para criar o primeiro usuário admin
                                .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                                //permissões para desastres
                                .requestMatchers(HttpMethod.GET, "/desastre/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/desastre").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/desastre").hasAuthority("ROLE_ADMIN")
                                //permissões para abrigos
                                .requestMatchers(HttpMethod.GET, "/abrigo/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/abrigo").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/abrigo").hasAuthority("ROLE_ADMIN")
                                //permissões para vítimas
                                .requestMatchers(HttpMethod.GET, "/vitima/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/vitima/vitimaabrigo").permitAll()
                                .requestMatchers(HttpMethod.GET, "/vitima/vitimadesastre").permitAll()
                                .requestMatchers(HttpMethod.POST, "/vitima").hasAnyAuthority("ROLE_ADMIN", "ROLE_VOLUNT")
                                .requestMatchers(HttpMethod.DELETE, "/vitima").hasAnyAuthority("ROLE_ADMIN", "ROLE_VOLUNT")
                                //permissões para voluntários
                                .requestMatchers(HttpMethod.GET, "/voluntario/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/voluntario").hasAnyAuthority("ROLE_ADMIN", "ROLE_VOLUNT")
                                .requestMatchers(HttpMethod.DELETE, "/voluntario").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()).addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
