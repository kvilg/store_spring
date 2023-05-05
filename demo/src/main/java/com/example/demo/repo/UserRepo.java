package com.example.demo.repo;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo  extends JpaRepository<User,Long> {

    public User findByLogin(String login);

    public User getByLogin(String login);

    public List<User> getByName(String login);

    User findByEmail(String email);
}
