package com.example.demo.servis;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repo.UserRepo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;



@Service
public class UserService implements UserDetailsService{



    @Autowired
    private UserRepo userData;


//    @PersistenceContext // or even @Autowired
//    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DefaultEmailService emailService;

    @Autowired
    private CodeGeneration codeGeneration;

    public UserService() {
    }

    public List<User> getAll() {
        return  this.userData.findAll();
    }
    public User getByLogin(String login) {
        return this.userData.getByLogin(login);
    }

    @Transactional
    public List<User> findUser(String name) throws Exception {

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        cr.select(root).where(cb.like(root.get("name"), "%"+name+"%"));

        Query<User> query = session.createQuery(cr);

        sessionFactory.close();

        return query.getResultList();
    }



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = getByLogin(login);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getPassword(), true, true, true, true, new HashSet<>());
    }

    public void registrationAdmin(String email,String name, String login,String password){
        User newUser = new User(email,name,login,password);
        newUser.setRole(Role.ADMIN);
        newUser.setStatusActive(true);
        userData.save(newUser);
    }

    @Transactional
    public void registration(String email,String name, String login,String password) throws Exception {
        if(login == null || password == null || email == null || name == null||
                login.equals("") || password.equals("") || email.equals("") || name.equals("")){
            throw new NullPointerException();
        }

        User user = userData.findByLogin(login);
        if(user != null){
            throw new Exception("user is created");
        }

        User userByEmail = userData.findByEmail(email);
        if(userByEmail != null){
            throw new Exception("email is used");
        }

        User newUser = new User(email,name,login,password);
        String code = codeGeneration.generate();
        newUser.setCodeActive(code);
        emailService.send(email,"CODE ACTIVE",code);
        userData.save(newUser);

    }

    @Transactional
    public void activeUser(String login,String code) throws Exception {
        if (login == null || login.equals("") || code == null || code.equals("")){
            throw new NullPointerException("login or code is null");
        }
        User user = userData.findByLogin(login);
        if(Objects.equals(user.getCodeActive(), code)){
            user.setCodeActive(null);
            user.setStatusActive(true);
            userData.save(user);
        }
    }




}
