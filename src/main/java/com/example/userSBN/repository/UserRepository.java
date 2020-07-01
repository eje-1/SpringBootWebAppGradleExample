package com.example.userSBN.repository;

import com.example.userSBN.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*CrudRepository Implementiert grundlegende CRUD-Operationen, einschließlich count, delete, deleteById, save, saveAll, findById und findAll.*/
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
}
