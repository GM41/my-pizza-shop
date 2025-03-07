package org.example.authservice;

import jakarta.validation.Valid;
import org.example.authservice.dto.AuthRequest;
import org.example.authservice.dto.UserDto;
import org.example.authservice.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(mapper.map(service.saveUser(user), UserDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@Valid @RequestBody AuthRequest request){
        return new ResponseEntity<>(service.generateToken(request.getLogin(), request.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "Token is valid!";
    }
}
