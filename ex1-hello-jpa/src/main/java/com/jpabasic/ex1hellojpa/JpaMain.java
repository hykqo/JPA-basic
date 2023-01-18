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
            //내부조인
            List<JMember> members = em.createQuery("select m from JMember m inner join m.team t", JMember.class).getResultList();
            //외부조인
            List<JMember> members1 = em.createQuery("select m from JMember m left join m.team t", JMember.class).getResultList();
            //세타조인
            List<JMember> members2 = em.createQuery("select m from JMember m, JTeam  t where m.username = t.name", JMember.class).getResultList();

            //on절 활용(조인 대상 필터링)
            List<JMember> members4 = em.createQuery("select m from JMember m left join m.team t on t.name = 'teamA'", JMember.class).getResultList();
            //on절 활용(연관관계 없는 조인)
            List<JMember> members5 = em.createQuery("select m from JMember m left join m.team t on t.name = m.username", JMember.class).getResultList();


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
