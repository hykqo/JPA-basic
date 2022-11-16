package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.hellojpa.HelloMember;
import com.jpabasic.ex1hellojpa.hellojpa.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            HelloMember member = new HelloMember();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차캐시에 있음.
            List<HelloMember> members = findTeam.getMembers();

            for(HelloMember m : members) System.out.println("m = " + m.getUsername());


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
