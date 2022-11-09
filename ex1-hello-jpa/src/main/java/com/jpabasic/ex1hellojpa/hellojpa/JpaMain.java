package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            System.out.println("===before===");
            em.persist(member);
            System.out.println("===after===");

            em.find(Member.class, 101L);
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getName() = " + member.getName());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
