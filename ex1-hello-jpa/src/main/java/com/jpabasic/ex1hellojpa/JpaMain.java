package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //반환 타입이 명확할 때 사용. ex)값타입일때 타입을 지정해서 반환가능하다.
            TypedQuery<String> typedQuery = em.createQuery("SELECT m.name FROM Member m", String.class);

            //반환 타입이 명확하지 않을때 사용.
            Query query = em.createQuery("SELECT m FROM Member m", Member.class);


            //query.getResultList() 결과가 하나 이상일 때 리스트 반환. 만약 결과가 없다면 빈 리스트 반환.
            List<Member> query1 = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();


            //결과가 정확히 하나, 단일 객체 반환
            TypedQuery<Member> query2 = em.createQuery("SELECT m FROM Member m", Member.class);
//            Member member = query2.getSingleResult();

            //파라미터 바인딩 - 이름 기준
            TypedQuery<Member> query3 = em.createQuery("SELECT m FROM Member m where m.name= :name", Member.class);
            query3.setParameter("name", "member1");

            //파라미터 바인딩 - 위치 기준
            TypedQuery<Member> query4 = em.createQuery("SELECT m FROM Member m where m.name=?1", Member.class);
            query4.setParameter(1, "member1");

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
