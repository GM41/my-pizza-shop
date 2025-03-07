package org.example.authservice;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.Calendar;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "login can`t be blank")
    @NotNull(message = "login can`t be null")
    private String login;
    @NotBlank(message = "password can`t be blank")
    @NotNull(message = "password can`t be null")
    private String password;
    @NotBlank(message = "email can`t be blank")
    @NotNull(message = "email can`t be null")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login= login;
    }
}
