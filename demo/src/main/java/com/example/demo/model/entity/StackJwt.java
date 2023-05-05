package com.example.demo.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Entity
public class StackJwt{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataJwt;

    public StackJwt(){

    }

    public StackJwt(String token){
        this.token = token;
        this.dataJwt = Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault()).toInstant());
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDataJwt() {
        return dataJwt;
    }

    public void setDataJwt(Date dataJwt) {
        this.dataJwt = dataJwt;
    }
}