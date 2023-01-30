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


            //JPQL 기본함수
            //CONCAT - 문자열 A와 B를 더한다
            List<String> result1 = em.createQuery("select concat('a','b') from JMember  m", String.class).getResultList();
            //하이버네이트는 해당 형식으로도 문자열을 더할 수 있다. uninject로 인텔리제이 옵션을 꺼야 사용 가능하다.
            List<String> result2 = em.createQuery("select 'a' ||'b' from JMember  m", String.class).getResultList();

            //SUBSTRING - 문자열 자르기
            List<String> result3 = em.createQuery("select substring(m.username, 2, 3) from JMember  m", String.class).getResultList();

            //LOCATE - 문자열의 인덱스 반환
            List<Integer> result4 = em.createQuery("select locate('cd','abcdefg') from JMember  m", Integer.class).getResultList();

            //SIZE - 특정 컬렉션의 크기 반환
            List<Integer> result5 = em.createQuery("select size(t.members) from JTeam  t", Integer.class).getResultList();

            //INDEX -값타입 컬렉션에서 컬렉션의 위치값을 구할때 사용
            @OrderColumn
            List<Integer> result6 = em.createQuery("select index(t.members) from JTeam  t", Integer.class).getResultList();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
