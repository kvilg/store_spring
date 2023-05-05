package com.example.demo.servis.product;

import com.example.demo.model.entity.Mouse;
import com.example.demo.model.MouseFindModelIn;
import com.example.demo.model.entity.MouseDpi;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceMouse extends ServiceProduct<Mouse>{



    @Transactional
    public List<Mouse> findList(MouseFindModelIn model)
//            String name,
//                                Integer minPrice, Integer maxPrice,
//                                Integer keyCountMin, Integer keyCountMax,
//                                Boolean illumination, Boolean gaming, Integer[] dpi)
    {

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Mouse> cr = cb.createQuery(Mouse.class);
        Root<Mouse> root = cr.from(Mouse.class);





        Join<Mouse, MouseDpi> join = root.join("dpi", JoinType.INNER);

// Добавляем условия для объединения таблиц
        Predicate predicate1 = cb.equal(root.get("dpi"), join.get("dpi"));

        cr.where(predicate1);


        Predicate namePredicate = null;
        if(model.getName() != null){
            namePredicate = cb.like(root.get("name"), "%"+model.getName()+"%");
        }

        Predicate pricePredicate = null;
        if(model.getMinPrice() != null && model.getMaxPrice() != null){
            pricePredicate = cb.between(root.get("price"), model.getMinPrice(), model.getMaxPrice());
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


//        Predicate dpiPredicate = null;
//        if(model.getDpi() != null){
//
//            dpiPredicate = (root.get("dpi").in(model.getDpi()));
//        }

        cr.select(root).
                where(cb.and(
                        namePredicate,
                        pricePredicate,
                        keyCountPredicate,
                        illuminationPredicate,
                        gamingPredicate));

        Query<Mouse> query = session.createQuery(cr);


        sessionFactory.close();


        return query.getResultList();
    }

}
