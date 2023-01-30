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

            //기본 case식
            String query = "select " +
                                    "case when m.age <= 10 then '학생요금' " +
                                    "     when m.age >= 60 then '학생요금' " +
                                    "     else '일반요금' " +
                                    "end " +
                            "from JMember m";
            List<String> result =  em.createQuery(query, String.class).getResultList();

            //coalesce 사용 -> 사용자 이름이 없으면 '이름 없는 회원' 반환
            List<String> result2 = em.createQuery("select coalesce(m.username, '이름 없는 회원') from JMember  m", String.class).getResultList();

            //nullIf 사용 -> 사용자 이름이 관리자면 Null반환  나머지는 본인의 이름 반환
            List<String> result3 = em.createQuery("select nullif(m.username, '관리자') from JMember  m", String.class).getResultList();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
