package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Item;
import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.domain.Order;
import com.jpabasic.ex1hellojpa.domain.OrderItem;
import com.jpabasic.ex1hellojpa.hellojpa.HelloMember;
import com.jpabasic.ex1hellojpa.hellojpa.Locker;
import com.jpabasic.ex1hellojpa.hellojpa.Team;

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
            Locker locker = new Locker();
            locker.setName("1L");
            em.persist(locker);

            HelloMember member = new HelloMember();
            member.setUsername("A");
            member.setLocker(locker);
            em.persist(member);

            locker.setMember(member);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
