package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.domain.Order;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.engine.transaction.internal.TransactionImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();
            Member member= em.find(Member.class, 1L);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
