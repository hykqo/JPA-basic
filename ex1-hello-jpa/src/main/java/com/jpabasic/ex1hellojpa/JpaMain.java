package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            em.createNativeQuery("select * from hellomember").getResultList();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
