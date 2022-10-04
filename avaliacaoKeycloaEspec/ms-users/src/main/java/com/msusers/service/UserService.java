package com.msusers.service;

import com.msusers.dto.UserDTO;
import com.msusers.models.Bill;
import com.msusers.models.User;
import com.msusers.repository.BillsFeign;
import com.msusers.repository.KeycloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private KeycloakRepository repository;

    @Autowired
    private BillsFeign feign;

    public UserDTO listingBillsbyUserId(String mappedId) {
        UserDTO user = UserDTO.userToDTO((User) repository.findById(mappedId));
        user.setBills(feign.findBills(user.getId()));
        return user;
    }

    public List<Bill> findAllBills() {
        return feign.listAll();
    }
}
