package com.example.demo.servis.product;

import com.example.demo.model.entity.Keyboard;

import com.example.demo.model.KeyboardFindModelIn;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceKeyboard extends ServiceProduct<Keyboard>{


    @Transactional
    public List<Keyboard> findList(KeyboardFindModelIn model){
        return new ArrayList<>();
    }

}
