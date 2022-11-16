package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.*;

@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private HelloMember member;
}
