package org.example.usermicroservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Calendar;


@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(message = "Login can`t be null")
    @NotBlank(message = "Login is required")
    private String login;
    @NotNull(message = "Email can`t be null")
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull(message = "Password can`t be null")
    @NotBlank(message = "Password is required")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
