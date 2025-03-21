package org.example.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "login can`t be null")
    @NotBlank(message = "login can`t be blank")
    private String login;
    @NotNull(message = "password can`t be null")
    @NotBlank(message = "password can`t be blank")
    private String password;
    @NotNull(message = "email can`t be null")
    @NotBlank(message = "email can`t be blank")
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
