package com.sonderben.kagom.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.EmployeeEntity;
import com.sonderben.kagom.service.CustomerService;
import com.sonderben.kagom.service.EmployeeService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getSubjectJwtFromRequest(request);
        DecodedJWT decodedJWT = null;

        if (StringUtils.hasText(jwt)) {
            decodedJWT = validateToken(jwt);
        }

        if (decodedJWT != null) {

            try {

                String email = decodedJWT.getSubject();

                UsernamePasswordAuthenticationToken authentication;
                System.out.println(decodedJWT.getClaims());
                if (decodedJWT.getClaim("roles").asList(String.class).contains("CUSTOMER")) {
                    System.err.println("roles customer");
                    CustomerEntity customer = customerService.findByEmail(email);

                    Collection<GrantedAuthority> grantedAuthorities =
                    customer.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
                    authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

                } else {
                    System.err.println("roles employees");
                    EmployeeEntity employee = employeeService.findByEmail(email);
                    Collection<GrantedAuthority> grantedAuthority =
                            employee.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
                    authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthority);
                }

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);


            } catch (Exception e) {
                System.err.println(e);
            }

        }
        filterChain.doFilter(request, response);


    }

    private String getSubjectJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.err.println("BT: "+bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public  DecodedJWT validateToken(String token) {
         String KEY = "awed8kfSdDSa8!m";
         Algorithm algorithm=Algorithm.HMAC256(KEY.getBytes());

        try {
            DecodedJWT a= JWT.require(algorithm).withIssuer("sonderben").build().verify(token);
            System.err.println("token: verified");
            return  a;
        } catch (JWTVerificationException e){
            System.err.println("func/validateToken"+e.getMessage());
            return null;
        }

    }

    /*public int diffBetweenDate(DecodedJWT decodedJWT){
        Date d1 =new Date(System.currentTimeMillis());
        System.out.println("current: "+d1.getTime());
        Date expiresAt= decodedJWT.getExpiresAt();
        long diff = expiresAt.getTime() - d1.getTime();
        System.err.println( TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) );
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }*/
}
