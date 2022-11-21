package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

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

            Team team2  = new Team();
            team2.setName("team2");
            em.persist(team2);

            HelloMember member = new HelloMember();
            member.setUsername("hello1");
            member.setTeam(team);
            em.persist(member);

            HelloMember member2 = new HelloMember();
            member2.setUsername("hello2");
            member2.setTeam(team2);
            em.persist(member2);

            HelloMember member3 = new HelloMember();
            member3.setUsername("hello3");
            member3.setTeam(team2);
            em.persist(member3);

            em.flush();
            em.clear();

            List<HelloMember> members = em.createQuery("select m from HelloMember m", HelloMember.class)
                    .getResultList();


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
