package com.example.demo.repo;


import com.example.demo.model.entity.StackJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepo extends JpaRepository<StackJwt,Long>{
    public StackJwt getByToken(String token);
}
