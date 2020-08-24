package com.example.userSBN.repository;

import com.example.userSBN.model.Faecher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepositoryFaecher extends JpaRepository<Faecher, Integer> {

    @Query(value = "SELECT name, abkuerzung, stufe FROM faecher WHERE to_tsvector(name) @@ to_tsquery(':keyword')", nativeQuery = true)
    public List<Faecher> search(@Param("keyword") String keyword);


}
