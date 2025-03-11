package org.example.usermicroservice;

import jakarta.persistence.EntityNotFoundException;
import org.example.usermicroservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public Page<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User save(UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    public User update(User user) {
        if(!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    public void deleteById(long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
