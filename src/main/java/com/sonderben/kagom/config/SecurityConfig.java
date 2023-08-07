package com.sonderben.kagom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {

    @Bean
    public JwtAuthenticationFilter jwtAuthFilter() {
        return new JwtAuthenticationFilter();
    }

    /*public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/path");
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).hasRole("USER")
                );
        return http.build();
    }*/
    @Bean
    public SecurityFilterChain configure(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("");
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> {
                    sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
               .authorizeHttpRequests((authz) -> authz
                               .requestMatchers(
                               antMatcher("/api/v1/auth/**"),

                               antMatcher("/v2/api-docs"),
                               antMatcher("/v3/api-docs"),
                               antMatcher("/v3/api-docs/**"),
                               antMatcher("/swagger-resources"),
                               antMatcher("/swagger-resources/**"),
                               antMatcher("/configuration/ui"),
                               antMatcher("/configuration/security"),
                               antMatcher("/swagger-ui/**"),
                               antMatcher("/webjars/**"),
                               antMatcher("/swagger-ui.html"),
                                       antMatcher("/customers")

                       ).hasAnyRole("EMPLOYEE","CUSTOMER")
                       /*.requestMatchers( mvcMatcherBuilder.pattern("/customers/**") )
                       .hasAnyRole("EMPLOYEE","CUSTOMER")*/
                               /*.requestMatchers(antMatcher("/customers/**"))
                               .hasAnyRole("EMPLOYEE")*/
                               .anyRequest().authenticated()

               )

               .headers(header -> {
                   header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
               })

               .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}






