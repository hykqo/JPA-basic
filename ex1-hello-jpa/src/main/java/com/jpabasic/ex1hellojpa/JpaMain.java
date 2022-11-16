package com.jpabasic.ex1hellojpa;

import com.jpabasic.ex1hellojpa.domain.Item;
import com.jpabasic.ex1hellojpa.domain.Member;
import com.jpabasic.ex1hellojpa.domain.Order;
import com.jpabasic.ex1hellojpa.domain.OrderItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //회원 생성
            Member member = new Member();
            member.setName("A");
            em.persist(member);
            //상품 생성
            Item item = new Item();
            item.setName("상품1");
            item.setPrice(100);
            em.persist(item);
            //주문 생성
            Order order = new Order();
            order.setMember(member);
            em.persist(order);
            //주문하는 상품 생성 및 연관관계 처리.
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setOrderPrice(item.getPrice());
            em.persist(orderItem);

            order.addOrderItem(orderItem);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
