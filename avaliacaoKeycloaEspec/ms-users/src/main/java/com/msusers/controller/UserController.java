package com.msusers.controller;

import com.msusers.dto.UserDTO;
import com.msusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("{/mappedId}")
    @RolesAllowed("USER}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> listAll(@PathVariable String mappedId) {
        return ResponseEntity.ok(
                service.listingBillsbyUserId(mappedId)
        );
    }

    @GetMapping("/all")
    @RolesAllowed("USER}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> allBills() {
        return ResponseEntity.ok().body(service.findAllBills());
    }

}
