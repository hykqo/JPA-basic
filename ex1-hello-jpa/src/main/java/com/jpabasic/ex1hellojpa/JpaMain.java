package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.HelloItem;
import com.jpabasic.ex1hellojpa.hellojpa.HelloMember;
import com.jpabasic.ex1hellojpa.jpql.JMember;
import com.jpabasic.ex1hellojpa.jpql.JTeam;
import com.jpabasic.ex1hellojpa.jpql.MemberTYPE;

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

            String query = "select function('group_concat', m.username) from JMember  m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for(String s : result) System.out.println("s = " + s);


            //하이버네이트에서는 해당 형식으로 지원을 한다 injectLanguage 를 끄고 해당 형식으로 작성해도 된다.
            String query = "select group_concat(m.username) from JMember  m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
