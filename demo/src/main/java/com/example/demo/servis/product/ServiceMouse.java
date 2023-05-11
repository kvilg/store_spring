package com.example.demo.servis.product;

import com.example.demo.model.entity.Mouse;
import com.example.demo.model.MouseFindModelIn;
import com.example.demo.model.entity.MouseDpi;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMouse extends ServiceProduct<Mouse>{



    @Transactional
    public List<Mouse> findList(MouseFindModelIn model){
        return new ArrayList<>();
    }

}
