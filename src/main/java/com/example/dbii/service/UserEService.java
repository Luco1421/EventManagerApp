package com.example.dbii.service;

import com.example.dbii.entity.UserE;
import com.example.dbii.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserEService {

    @Autowired
    UserERepository userRepository;

    @Transactional
    public void registerUser(String name, String lastName, Long phone, String email, String password) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Ya existe una cuenta registrada a este email");
        }
        UserE user = new UserE();
        user.setUserName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsEmployee(false);

        userRepository.save(user);
        userRepository.flush();
    }

    public int processLogin(String email, String password) {
        Optional<UserE> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isEmpty()) { return 0; }
        if (!userByEmail.get().getPassword().equals(password)) { return 50; }
        return 100;
    }

}
