package com.example.resourceserver.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
                .hasRole("developer")
                .anyRequest().authenticated() // make all the requests to application to be authenticated and authorized
                .and()
                .oauth2ResourceServer() // it will created the token authentication filter that will intercept the requests
                .jwt() // and it will extract the authorization before a token to be a jwt and see if it's valid
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }
}
