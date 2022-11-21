package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Team team  = new Team();
            team.setName("team1");
            em.persist(team);
            
            HelloMember member = new HelloMember();
            member.setUsername("hello1");
            member.setTeam(team);
            em.persist(member);
            
            em.flush();
            em.clear();

            HelloMember member1 = em.find(HelloMember.class, member.getId());
            System.out.println("member1.getClass() = " + member1.getClass());
            System.out.println("member1.getTeam().getClass() = " + member1.getTeam().getClass());

            System.out.println("======");
            member.getTeam().getName(); //이떄 초기화가 실행됨.
            System.out.println("======");
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
