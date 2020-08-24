package com.example.userSBN.services;

import com.example.userSBN.model.Faecher;
import com.example.userSBN.repository.FaecherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaecherService {

    @Autowired
    FaecherRepository repositoryFaecher;

    public List<Faecher> listAll(String keyword){
        if (keyword != null){
            return repositoryFaecher.search(keyword);
        }
        return repositoryFaecher.findAll();
    }
}
