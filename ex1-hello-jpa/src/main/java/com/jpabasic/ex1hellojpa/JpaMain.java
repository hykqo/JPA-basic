package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.jpql.JMember;
import com.jpabasic.ex1hellojpa.jpql.jdto.JMemberDTO;

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
            Query query = em.createQuery("SELECT m FROM Member m");


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

            //프로젝션 - 엔티티 프로젝션을 조회하면, 영속성컨텍스트에 전부 관리가 된다.
            JMember member = new JMember();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            //Query 타입으로 조회
            List resultList1 = em.createQuery("SELECT m.username, m.age FROM JMember m").getResultList();
            Object[] result = (Object[]) resultList1.get(0);
            for(Object o : result) System.out.println("o = " + o);
            //오브젝트 타입으로 조회
            List<Object[]> resultList2 = em.createQuery("SELECT m.username, m.age FROM JMember m").getResultList();
            Object[] result2 = resultList2.get(0);
            for(Object o : result2) System.out.println("o = " + o);
            //new 명령어로 조회
            List<JMemberDTO> resultList3 = em.createQuery("SELECT new com.jpabasic.ex1hellojpa.jpql.jdto.JMemberDTO(m.username, m.age) FROM JMember m", JMemberDTO.class).getResultList();
            System.out.println("resultList3.get(0).getUsername() = " + resultList3.get(0).getUsername());
            System.out.println("resultList3.get(0).getAge() = " + resultList3.get(0).getAge());

            //페이징
            for(int i = 0; i < 100; i++){
                JMember member2 = new JMember();
                member2.setUsername("member" + i);
                member2.setAge(i);
                em.persist(member2);
            }

            em.flush();
            em.clear();

            List<JMember> resultList4 = em.createQuery("select m from JMember m order  by m.age desc", JMember.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("resultList4.size() = " + resultList4.size());
            for(JMember m : resultList4) System.out.println("m = " + m);

            
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
