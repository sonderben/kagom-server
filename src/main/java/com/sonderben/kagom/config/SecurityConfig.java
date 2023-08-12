package com.sonderben.kagom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http/*, HandlerMappingIntrospector introspector*/) throws Exception {
        //MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("");
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
                                antMatcher("/swagger-ui.html"),
                                antMatcher("/webjars/**"),
                                antMatcher("/swagger-ui/index.html"),
                                antMatcher("/h2-console/"),
                                antMatcher("/h2-console/**"),
                                antMatcher("/customers/login"),
                                antMatcher("/customers/login/"),
                                antMatcher("/customers/signup"),
                                antMatcher("/customers/signup/")
                        ).permitAll()
                        .requestMatchers(
                                antMatcher(HttpMethod.GET,"/customers/{id}"),
                                antMatcher(HttpMethod.GET,"/customers/light/{id}"),
                                antMatcher(HttpMethod.GET,"/customers/full/{id}"),
                                antMatcher(HttpMethod.GET,"/shipments/{id}")
                        ).hasAnyAuthority("CUSTOMER","ADMIN","EMPLOYEE")
                        .requestMatchers(
                                antMatcher(HttpMethod.PUT,"/customers/{id}")
                        ).hasAnyAuthority("CUSTOMER")
                        .requestMatchers(
                                antMatcher(HttpMethod.DELETE,"/customers/{id}")
                        ).hasAnyAuthority("CUSTOMER","ADMIN")
                        .requestMatchers(
                                antMatcher(HttpMethod.GET,"/employees/{id}"),
                                antMatcher(HttpMethod.GET,"/employees/light/{id}"),
                                antMatcher(HttpMethod.GET,"/employees/full/{id}")
                        ).hasAnyAuthority("CUSTOMER","ADMIN","EMPLOYEE")
                        .requestMatchers(
                                antMatcher(HttpMethod.PUT,"/employees/{id}")
                        ).hasAnyAuthority("CUSTOMER")
                        .requestMatchers(
                                antMatcher(HttpMethod.DELETE,"/employees/{id}")
                        ).hasAnyAuthority("CUSTOMER","ADMIN")










                        .anyRequest()./*hasAuthority("ADMIN")*/permitAll()//authenticated()

                )

                .headers(header -> {
                    header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })

                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}






