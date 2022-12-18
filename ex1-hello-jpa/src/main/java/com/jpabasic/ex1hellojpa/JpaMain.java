package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HelloMember> query = cb.createQuery(HelloMember.class);

            //루트 클래스(조회를 시작할 클래스)
            Root<HelloMember> m = query.from(HelloMember.class);

            //쿼리생성
            CriteriaQuery<HelloMember> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<HelloMember> resultList = em.createQuery(cq).getResultList();

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
