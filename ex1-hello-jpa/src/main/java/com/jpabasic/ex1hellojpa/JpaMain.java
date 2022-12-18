package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.hellojpa.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            HelloMember member = new HelloMember();
            member.setUsername("member1");
            member.setHomeAddress(new HelloAddress("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddresseHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddresseHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            //컬렉션안의 치킨 -> 한식
            System.out.println("===========START============");
            HelloMember member1 = em.find(HelloMember.class, member.getId());
            HelloAddress a  = member1.getHomeAddress();

            //치킨 -> 한식
            member1.getFavoriteFoods().remove("치킨");
            member1.getFavoriteFoods().add("한식");

//            member1.getAddresseHistory().remove(new AddressEntity("old2", "street", "10000"));
//            member1.getAddresseHistory().add(new AddressEntity("newCity2", "street", "10000"));

            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
