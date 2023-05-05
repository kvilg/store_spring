package com.example.demo.servis;

import com.example.demo.model.entity.StackJwt;
import com.example.demo.repo.JwtRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;




@Service
public class JwtService {
    

    @Autowired
    private JwtRepo jwtData;

//    @PersistenceContext // or even @Autowired
//    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;


    

    public boolean findToken(String token){

        StackJwt jwtModel = jwtData.getByToken(token);

        return jwtModel != null;

    }

    public void addTokenUse(String token){
        jwtData.save(new StackJwt(token));

    }


    @Transactional
    public void rewoveOldDate() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Session session = sessionFactory.openSession();



        Date nowDate = formatter.parse(String.valueOf(LocalDateTime.now().minusMonths(1).minusDays(1)));

        String myDate = "0-00-0000T00:00:00";
        Date fromDate = formatter.parse(myDate);



        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StackJwt> cr = cb.createQuery(StackJwt.class);
        Root<StackJwt> root = cr.from(StackJwt.class);



        cr.select(root).where(cb.between(root.get("dataJwt"), fromDate, nowDate));

        Query<StackJwt> query = session.createQuery(cr);



        List<StackJwt> jwtList = query.getResultList();
        for (int i = 0; i < jwtList.size(); i++) {
            jwtData.delete(jwtList.get(i));
        }

        sessionFactory.close();
    }



}
