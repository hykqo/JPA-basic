package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.jpql.JMember;
import com.jpabasic.ex1hellojpa.jpql.JTeam;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            JTeam team = new JTeam();
            team.setName("teamA");
            em.persist(team);

            JMember member = new JMember();
            member.setUsername("team1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //select 서브쿼리
            List<JMember> members = em.createQuery("select (select avg(m1.age) from JMember m1) as avgAGE from JMember m join Team t", JMember.class).getResultList();
            //from절은 불가

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
