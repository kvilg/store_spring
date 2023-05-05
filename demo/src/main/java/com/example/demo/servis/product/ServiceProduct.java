package com.example.demo.servis.product;

import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repo.ProductRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class ServiceProduct <T extends Product>{

    @Autowired
    ProductRepo<T> data;

    @Autowired
    SessionFactory sessionFactory;

    public void create(T t, User user) throws Exception {

        if(user.getRole() != Role.ADMIN){
            throw new Exception("user not admin");
        }

        if (t.getId() != null){
            throw new Exception("id is not null");
        }
        data.save(t);
    }

    @Transactional
    public void update(T t, User user) throws Exception {

        if(user.getRole() != Role.ADMIN){
            throw new Exception("user not admin");
        }

        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        data.save(t);
    }

    public void delete(T t, User user) throws Exception {
        if(user.getRole() != Role.ADMIN){
            throw new Exception("user not admin");
        }
        data.deleteById(t.getId());
    }

}
