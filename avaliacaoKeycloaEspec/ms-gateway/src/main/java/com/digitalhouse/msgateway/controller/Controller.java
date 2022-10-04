package com.digitalhouse.msgateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class Controller {

    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    }
}
