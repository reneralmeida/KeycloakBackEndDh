package com.msusers.dto;

import com.msusers.models.Bill;
import com.msusers.models.User;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private List<Bill> bills;

    public static UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstname(user.getFirstName());
        dto.setBills(user.getBills());
        return dto;
    }
}
