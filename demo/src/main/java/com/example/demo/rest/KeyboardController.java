package com.example.demo.rest;

import com.example.demo.model.*;
import com.example.demo.model.entity.Keyboard;
import com.example.demo.servis.product.ServiceKeyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyboard")
public class KeyboardController extends ProductController<Keyboard> {

    @Autowired
    private ServiceKeyboard serviceKeyboard;

    @PostMapping(value="/find")
    public ResponseEntity<?> find(@RequestBody KeyboardFindModelIn json){
        try {
            List<Keyboard> keyboardList = serviceKeyboard.findList(json);
            return new ResponseEntity<>(keyboardList, HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
