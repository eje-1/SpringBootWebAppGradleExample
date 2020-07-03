package com.example.userSBN.services;

import com.example.userSBN.model.User;
import com.example.userSBN.repository.SearchRepository;
import com.example.userSBN.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    SearchRepository repo;


        public List<User> listAll(String keyword){
            if (keyword != null){
                return repo.search(keyword);
            }
            return repo.findAll();
        }

    }
