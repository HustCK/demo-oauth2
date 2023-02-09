package com.example.resourceserver.controllers;

import com.example.resourceserver.model.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    @PreAuthorize("hasAuthority('ROLE_developer') and #id==#jwt.subject")
    //@Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id " + id + " and jwt subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId==#jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String name = (String) (jwt.getClaims().get("name"));
        String firstName = name.split(" ")[0];
        String lastName = name.split(" ")[1];
        return new UserRest(firstName, lastName, id);
    }
}
