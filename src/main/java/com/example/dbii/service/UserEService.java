package com.example.dbii.service;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.UserE;
import com.example.dbii.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserEService {

    @Autowired
    UserERepository userRepository;

    public UserE getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public Set<UserE> getUserByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public boolean isUserEmployee(String email) {
        return userRepository.checkIfEmployee(email) == 1;
    }

    @Transactional
    public Long registerUser(String name, String lastName, Long phone, String email, String password, boolean admin) {
        if (userRepository.findByEmail(email).isPresent()) {
            return -1L;
        }
        UserE user = new UserE();
        user.setUserName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsEmployee(admin);

        userRepository.save(user);
        return user.getId();
    }

    public int processLogin(String email, String password) {
        Optional<UserE> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isEmpty()) { return 0; }
        if (!userByEmail.get().getPassword().equals(password)) { return 50; }
        return 100;
    }

    public boolean isDropeable(Long userId) {
        return userRepository.checkUserReservation(userId) == 0;
    }

    @Transactional
    public void deleteUserById(Long userId) {
        if (userRepository.findById(userId).isPresent() && isDropeable(userId)) userRepository.deleteById(userId);
    }

}
