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

            //fetch join없이 조회
            List<JMember> result = em.createQuery("select m from JMember m", JMember.class).getResultList();

            //연관관계는 성능향상을 위해서 Lazy(지연로딩)으로 설정해두었다.
            // 이경우에 만약 fetch Join을 사용하지 않았을 경우 m.getTeam()을 db에 보유한 만큼 가져온다.
            //ex)
            // 회원1 , 팀A(SQL)
            // 회원2 , 팀A(1차캐시)
            // 회원3 , 팀B(SQL)
            //최악의 경우 N+1이슈가 생긴다.
            for(JMember m : result) {
                System.out.println("m.getUsername() = " + m.getUsername());
                System.out.println("m.getTeam().getName() = " + m.getTeam().getName());
            }

            //fetch join 사용
            //한방쿼리 발생
            List<JMember> result2 = em.createQuery("select m from JMember m join fetch m.team", JMember.class).getResultList();

            for(JMember m : result) {
                System.out.println("m.getUsername() = " + m.getUsername());
                System.out.println("m.getTeam().getName() = " + m.getTeam().getName());
            }

            //Collection fetch join
            List<JTeam> result3 = em.createQuery("select t from JTeam t join fetch t.members", JTeam.class).getResultList();

            for(JTeam t : result3) System.out.println("t.getName() = " + t.getName() + ", [members="+t.getMembers()+"]");



            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
