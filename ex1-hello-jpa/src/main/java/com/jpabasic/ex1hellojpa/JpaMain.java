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
            Address address = new Address("city", "street", "zipcode");
            HelloMember member = new HelloMember();
            member.setUsername("hello");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            HelloMember member2 = new HelloMember();
            member2.setUsername("hello2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);


            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
