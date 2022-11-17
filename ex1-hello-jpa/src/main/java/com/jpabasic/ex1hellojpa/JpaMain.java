package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Item;
import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.domain.Order;
import com.jpabasic.ex1hellojpa.domain.OrderItem;
import com.jpabasic.ex1hellojpa.hellojpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Movie movie = new Movie();
            movie.setDirect("aaa");
            movie.setActor("bbb");
            movie.setName("영화제목");
            movie.setPrice(1000);
            em.persist(movie);
            
            em.flush();
            em.clear();

            HelloItem helloItem = em.find(HelloItem.class, movie.getId());
            System.out.println("helloItem = " + helloItem.getName());

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
