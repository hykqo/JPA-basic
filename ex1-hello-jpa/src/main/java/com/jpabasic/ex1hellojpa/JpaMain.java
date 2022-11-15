package com.jpabasic.ex1hellojpa;

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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            HelloMember member = new HelloMember();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //조회
            HelloMember findMember = em.find(HelloMember.class, member.getId());
            List<HelloMember> memberList = findMember.getTeam().getMembers();
            for(HelloMember m : memberList) System.out.println("m.getUsername() = " + m.getUsername());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
