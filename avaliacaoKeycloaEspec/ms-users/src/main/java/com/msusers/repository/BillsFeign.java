package com.msusers.repository;

import com.msusers.models.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "ms-bill")
public interface BillsFeign {

    @GetMapping("/bills/all/{customerBill}")
    List<Bill> findBills(@PathVariable String customerBill);

    @GetMapping("/bills/all")
    List<Bill> listAll();
}
