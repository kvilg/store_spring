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
import java.util.List;

@Service
public class ServiceKeyboard extends ServiceProduct<Keyboard>{


    @Transactional
    public List<Keyboard> findList(KeyboardFindModelIn model){

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Keyboard> cr = cb.createQuery(Keyboard.class);
        Root<Keyboard> root = cr.from(Keyboard.class);

        Predicate namePredicate = null;
        if(model.getName() != null){
            namePredicate = cb.like(root.get("name"), "%"+model.getName()+"%");
        }

        Predicate pricePredicate = null;
        if(model.getMinPrice() != null && model.getMaxPrice() != null){
            pricePredicate = cb.between(root.get("price"),
                                        model.getMinPrice(),
                                        model.getMaxPrice());
        }else if(model.getMinPrice() != null){
            pricePredicate = cb.ge(root.get("price"), model.getMinPrice());
        }else if(model.getMaxPrice() != null){
            pricePredicate = cb.le(root.get("price"), model.getMaxPrice());
        }

        Predicate keyCountPredicate = null;
        if(model.getKeyCountMin() != null && model.getKeyCountMax() != null){
            keyCountPredicate = cb.between(root.get("keyCount"),
                                            model.getKeyCountMin(),
                                            model.getKeyCountMax());
        }else if(model.getKeyCountMin() != null){
            keyCountPredicate = cb.ge(root.get("keyCount"), model.getKeyCountMin());
        }
        else if(model.getKeyCountMax() != null){
            keyCountPredicate = cb.le(root.get("keyCount"), model.getKeyCountMax());
        }

        Predicate illuminationPredicate = null;
        if(model.getIllumination() != null){
            illuminationPredicate = cb.equal(root.get("illumination"), model.getIllumination());
        }

        Predicate gamingPredicate = null;
        if(model.getGaming() != null){
            gamingPredicate = cb.equal(root.get("gaming"), model.getGaming());
        }

        cr.select(root).
                where(cb.and(
                    namePredicate,
                    pricePredicate,
                    keyCountPredicate,
                    illuminationPredicate,
                    gamingPredicate));

        Query<Keyboard> query = session.createQuery(cr);


        sessionFactory.close();


        return query.getResultList();
    }

}
