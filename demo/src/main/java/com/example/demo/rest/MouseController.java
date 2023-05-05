package com.example.demo.rest;


import com.example.demo.model.*;
import com.example.demo.model.entity.Mouse;
import com.example.demo.servis.product.ServiceMouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mouse")
public class MouseController extends ProductController<Mouse>{

    @Autowired
    private ServiceMouse serviceMouse;



    @PostMapping(value="/find")
    public ResponseEntity<?> find(@RequestBody MouseFindModelIn json){
        try {
            List<Mouse> mouseList = serviceMouse.findList(json);
            return new ResponseEntity<>(mouseList, HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
