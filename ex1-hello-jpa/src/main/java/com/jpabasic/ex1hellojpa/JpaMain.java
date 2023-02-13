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


            //엔티티값으로 조회시 sql은 기본키값으로 조회
            List<JMember> result = em.createQuery("select m from Member m where m = :member", JMember.class)
                    .getResultList();
            for(JMember i : result) System.out.println(i.getUsername());

            //연관된 엔티티값으로 조회시 sql은 외래키값으로 조회
            List<JMember> result2 = em.createQuery("select m from JMember m where m.team.id = :teamId", JMember.class)
                    .getResultList();
            for(JMember i : result) System.out.println(i.getUsername());

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
