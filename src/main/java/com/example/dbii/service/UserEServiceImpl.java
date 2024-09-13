package com.example.dbii.service;

import com.example.dbii.entity.UserE;
import com.example.dbii.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEServiceImpl implements UserEService {

    @Autowired
    UserERepository repository;

    @Override
    public Iterable<UserE> getAllUsers() {
        return repository.findAll();
    }
}
