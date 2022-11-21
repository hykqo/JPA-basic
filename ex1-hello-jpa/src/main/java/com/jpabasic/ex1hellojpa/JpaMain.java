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
            HelloMember member = new HelloMember();
            member.setUsername("hello1");
            em.persist(member);

            em.flush();
            em.clear();

            //proxy 예제
//            System.out.println("-------getReference 호출 시작-------------");
//            HelloMember referenceMember = em.getReference(HelloMember.class, member.getId());
//            System.out.println("-------getReference 호출 종료-------------");
//            System.out.println("referenceMember.getClass() = " + referenceMember.getClass());
//            System.out.println("referenceMember = " + referenceMember.getUsername());

            //find로 조회한 entity객체와 getReference로 조회한 proxy객체 비교.
            HelloMember m1 = em.find(HelloMember.class, member.getId());
            em.clear();
            HelloMember m2 = em.getReference(HelloMember.class, member.getId());
            System.out.println("findMember1 == referenceMember : " + (m1.getClass() == m2.getClass()));
            em.clear();

            //영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환.
            HelloMember m3 = em.find(HelloMember.class, member.getId());
            HelloMember m4 = em.getReference(HelloMember.class, member.getId());
            System.out.println("m3.getClass() = " + m3.getClass());
            System.out.println("m4.getClass() = " + m4.getClass());
            em.clear();

            System.out.println("---");

            //영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면 문제 발생
            HelloMember m5 = em.getReference(HelloMember.class, member.getId());
            System.out.println("m5.getClass() = " + m5.getClass());
            HelloMember m6 = em.find(HelloMember.class, member.getId());
            System.out.println("m6.getClass() = " + m6.getClass());
            System.out.println("m5 == m6 : " + (m5.getClass() == m6.getClass()));
            System.out.println(m5.getUsername());
            em.clear();

            //영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면 문제 발생
//            HelloMember m7 = em.getReference(HelloMember.class, member.getId());
//            em.detach(m7);
//            m7.getUsername();

            //프록시 인스턴스의 초기화 여부 확인기능.
            HelloMember m8 = em.getReference(HelloMember.class, member.getId());
            System.out.println("getPersistenceUnitUtil.isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(m8));
            Hibernate.initialize(m8);
            System.out.println("getPersistenceUnitUtil.isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(m8));

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
