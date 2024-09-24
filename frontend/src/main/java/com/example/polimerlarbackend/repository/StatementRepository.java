package com.example.polimerlarbackend.repository;


import com.example.polimerlarbackend.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement,Long> {
    boolean deleteById(long statId);
}
