package org.example.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "email can`t be blank")
    @NotNull(message = "email can`t be null")
    private String email;
    @NotBlank(message = "password can`t be blank")
    @NotNull(message = "password can`t be null")
    private String password;
    @NotBlank(message = "login can`t be blank")
    @NotNull(message = "login can`t be null")
    private String login;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
