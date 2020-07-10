package com.example.userSBN.repository;

import com.example.userSBN.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*CrudRepository Implementiert grundlegende CRUD-Operationen, einschließlich count, delete, deleteById, save, saveAll, findById und findAll.*/
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    List<User> findByName(String name);

    @Query(value = "SELECT u FROM User u WHERE u.name = :keyword or u.vorname = :keyword or u.email = :keyword")
    public List<User> search(@Param("keyword") String keyword);
}
