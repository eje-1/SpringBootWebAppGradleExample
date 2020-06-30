package com.example.userSBN.repository;

import com.example.userSBN.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    //returns list of Entry objects where String title matches title field
    List<User> findByTitle(@Param("name") String name);
}
