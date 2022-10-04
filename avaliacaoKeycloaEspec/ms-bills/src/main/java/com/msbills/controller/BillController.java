package com.msbills.controller;

import com.msbills.dto.BillDTO;
import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    @Autowired
    private BillService service;

    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("/user")
    @RolesAllowed("USER")
    public ResponseEntity<Principal> index(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @PostMapping("/all")
    @RolesAllowed("SUPPLIER")
    public ResponseEntity<Bill> save(@RequestBody BillDTO bill) {
        return ResponseEntity.ok(service.save(bill.toBill()));
    }

    @GetMapping("/all/{customerBill}")
    @RolesAllowed("USER")
    public ResponseEntity<List<Bill>> getAllByUser(@PathVariable String customerBill) {
        return ResponseEntity.ok().body(service.byCustomer(customerBill));}
}
