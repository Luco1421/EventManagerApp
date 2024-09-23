package com.example.dbii.service;

import com.example.dbii.entity.Type;
import com.example.dbii.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public boolean existsById(Long id) {
        return typeRepository.existsById(id);
    }
}
