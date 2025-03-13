package org.example.authservice.service;

import org.example.authservice.User;
import org.example.authservice.UserRepository;
import org.example.authservice.config.UserMapper;
import org.example.authservice.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;


    public UserDto saveUser(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setLogin(dto.getLogin());
        return userMapper.entityToDto(repository.save(user));
    }

    public String generateToken(String name, String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        if (authenticate.isAuthenticated()){
            return jwtService.generateToken(name);
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
