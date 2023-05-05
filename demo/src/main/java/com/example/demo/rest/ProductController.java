package com.example.demo.rest;

import com.example.demo.model.ExceptionModel;
import com.example.demo.model.entity.Product;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.servis.UserService;
import com.example.demo.servis.product.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


public class ProductController<T extends Product> {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService service;

    @Autowired
    private ServiceProduct<T> serviceProduct;




    @PostMapping(value="/create")
    public ResponseEntity<?> create(@RequestBody T json,
                                         @RequestHeader("Authorization") String token){
        try {
            String login = jwtTokenUtil.getUsernameFromToken(token);
            serviceProduct.create(json,service.getByLogin(login));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody T json,
                                         @RequestHeader("Authorization") String token){
        try {
            String login = jwtTokenUtil.getUsernameFromToken(token);
            serviceProduct.update(json,service.getByLogin(login));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/delete")
    public ResponseEntity<?> delete(@RequestBody T json,
                                         @RequestHeader("Authorization") String token){
        try {
            String login = jwtTokenUtil.getUsernameFromToken(token);
            serviceProduct.delete(json,service.getByLogin(login));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
