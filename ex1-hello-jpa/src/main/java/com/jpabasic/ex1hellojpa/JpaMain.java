package com.jpabasic.ex1hellojpa;

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

            //경로표현식 - 단일 값 연관필드 - 묵시적 조인 발생. 탐색 O
            List<JTeam> result = em.createQuery("select m.team from JMember m", JTeam.class).getResultList();
            for(JTeam s : result) System.out.println("s = " + s);

            //경로표현식 - 컬렉션 값 연관필드 - 묵시적 조인 발생, 탐색 X
            List<Collection> result2 = em.createQuery("select t.members from JTeam t", Collection.class).getResultList();
//            for(Collection s : result2) System.out.println("s = " + s);

            //경로표현식 - 컬렉션 값 연관필드 - 실무 활용 size
            List<Integer> result3 = em.createQuery("select size(t.members) from JTeam t", Integer.class).getResultList();
            System.out.println(result3);

            //경로표현식 - 명시적 조인 사용
            List<Integer> result4 = em.createQuery("select size(t.members) from JTeam t", Integer.class).getResultList();
            System.out.println(result3);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
