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



//            //fetch join의 한계. 데이터 정합성이 맞지 않을 수 있다.
//            List<JTeam> result1 = em.createQuery("select distinct t from JTeam t join fetch t.members", JTeam.class).getResultList();
//            for(JTeam t : result1) System.out.println("t.getName() = " + t.getName() + ", [members="+t.getMembers()+"]");
//
//            //쓸거면 객체 그래프를 탐색하듯이만 쓰자.
//            List<JTeam> result2 = em.createQuery("select distinct t from JTeam t join fetch t.members m join fetch m.team", JTeam.class).getResultList();
//            for(JTeam t : result2) System.out.println("t.getName() = " + t.getName() + ", [members="+t.getMembers()+"]");

            //컬렉션은 페치 조인 하면 페이징을 처리할 수 없다,
//            List<JTeam> result3 = em.createQuery("select t from JTeam t join fetch t.members m", JTeam.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//            for(JTeam t : result3) System.out.println("t.getName() = " + t.getName() + ", [members="+t.getMembers()+"]");

            //일대다인경우는 보통 다대일로 양방향을 매핑해놓은 경우가 많다.
            //디대일로 조회를 하자.
//            List<JMember> result4 = em.createQuery("select m from JMember m join fetch m.team t", JMember.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//            for(JMember m : result4) System.out.println("t.getName() = " + m.getTeam().getName() + ", [member="+m.getUsername()+"]");


            //컬렉션 페이징을 죽어도 써야겠다. 그러면 이렇게 하자.
            List<JTeam> result5 = em.createQuery("select t from JTeam t", JTeam.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            for(JTeam t : result5) System.out.println("t.getName() = " + t.getName() + ", [members="+t.getMembers()+"]");


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
