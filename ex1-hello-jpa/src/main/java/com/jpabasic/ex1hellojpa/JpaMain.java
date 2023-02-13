package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.HelloItem;
import com.jpabasic.ex1hellojpa.hellojpa.HelloMember;
import com.jpabasic.ex1hellojpa.jpql.JMember;
import com.jpabasic.ex1hellojpa.jpql.JTeam;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            JTeam teamA = new JTeam();
            teamA.setName("teamA");
            em.persist(teamA);

            JTeam teamB = new JTeam();
            teamB.setName("teamB");
            em.persist(teamB);

            JMember member1 = new JMember();
            member1.setUsername("member1");
            member1.setAge(10);
            member1.setTeam(teamA);
            em.persist(member1);

            JMember member2 = new JMember();
            member2.setUsername("member2");
            member2.setAge(10);
            member2.setTeam(teamA);
            em.persist(member2);

            JMember member3 = new JMember();
            member3.setUsername("member3");
            member3.setAge(10);
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();


            //다형성 쿼리 type전략
            List<HelloItem> result = em.createQuery("select i from HelloItem  i where type(i) in (HelloBook , HelloMovie)", HelloItem.class)
                    .getResultList();
            for(HelloItem i : result) System.out.println(i.getName());

            //다형성 쿼리 treat전략 (다운캐스팅)
            List<HelloItem> result2 = em.createQuery("select i from HelloItem  i where treat(i as HelloBook).Autoor = 'kim'", HelloItem.class)
                    .getResultList();
            for(HelloItem i : result) System.out.println(i.getName());

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
