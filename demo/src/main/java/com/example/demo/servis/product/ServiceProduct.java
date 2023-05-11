package com.example.demo.servis.product;

import com.example.demo.model.entity.Product;

import com.example.demo.model.entity.User;
import com.example.demo.repo.ProductRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;

public class ServiceProduct <T extends Product>{

    @Autowired
    ProductRepo<T> data;

    @Autowired
    SessionFactory sessionFactory;


    public void create(T t) throws Exception {

        if (t.getId() != null){
            throw new Exception("id is not null");
        }
        data.save(t);
    }

    @Transactional
    public void update(T t) {
        data.save(t);
    }

    public void delete(T t) {
        data.delete(t);
    }

}
