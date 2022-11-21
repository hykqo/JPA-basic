package com.jpabasic.ex1hellojpa.domain;

import com.jpabasic.ex1hellojpa.hellojpa.BaseEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus delieveryStatus;
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}
