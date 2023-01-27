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

            //enumtype 패키지 사용법
            List<JMember> members = em.createQuery("select m  from JMember m join Team t where m.memberTYPE= com.jpabasic.ex1hellojpa.jpql.MemberTYPE.ADMIN", JMember.class).getResultList();

            //enumtype 파라미터 바인딩 사용법
            List<JMember> members2 = em.createQuery("select m  from JMember m join Team t where m.memberTYPE= :type", JMember.class)
                    .setParameter("type", MemberTYPE.ADMIN)
                    .getResultList();

            //엔티티타입 사용법 - 상속관계에서 사용.
            List<HelloItem> item = em.createQuery("select i from Item i where type(i) = Book", HelloItem.class).getResultList();


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
