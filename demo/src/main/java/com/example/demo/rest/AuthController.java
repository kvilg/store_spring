package com.example.demo.rest;



import com.example.demo.model.Constants;
import com.example.demo.model.ExceptionModel;
import com.example.demo.model.entity.User;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserOut;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.example.demo.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserService service;



    @PostMapping(value="/login")
    public ResponseEntity<?> getAuthUser(@RequestBody UserAuth json) {

        try {
            json = new UserAuth(json);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(json.getLogin(), json.getPassword()));
            User user = service.getByLogin(json.getLogin());
            System.out.println("sssss");
            String token = jwtTokenUtil.generateToken(user);
            System.out.println(token);
            UserOut g = new UserOut(user);
            MultiValueMap<String,String> headers = new HttpHeaders();
            headers.add("Authorization",Constants.TOKEN_PREFIX+token);
            return new ResponseEntity<>(g, headers,HttpStatus.OK);
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.UNAUTHORIZED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(path = "/registration")
    public ResponseEntity<?>  registration(@RequestBody UserAuth json) {

        try {
            json = new UserAuth(json);
            service.registration(json.getEmail(),json.getName(),json.getLogin(),json.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "/active")
    public ResponseEntity<?> active(@RequestBody UserAuth json) {
        try {
            json = new UserAuth(json);
            service.activeUser(json.getLogin(),json.getCode());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionModel(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
