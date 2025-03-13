package org.example.authservice.config;

import org.example.authservice.User;
import org.example.authservice.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto entityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
