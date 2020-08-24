package com.example.userSBN.repository;

import com.example.userSBN.model.Faecher;
import com.example.userSBN.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname FROM users WHERE to_tsvector(name) @@ to_tsquery(':keyword')", nativeQuery = true)
    public List<User> search(@Param("keyword") String keyword);


}
