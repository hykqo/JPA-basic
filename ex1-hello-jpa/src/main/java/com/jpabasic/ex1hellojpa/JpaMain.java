package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Child child1 = new Child();
            child1.setName("child1");
            Child child2 = new Child();
            child2.setName("child2");

            Parent parent = new Parent();
            parent.setName("parent1");
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            em.flush();
            em.clear();

            Parent findP = em.find(Parent.class, parent.getId());
            em.remove(findP);
            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }

    private static void printMemberAndTeam(HelloMember member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}
