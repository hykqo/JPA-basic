package com.jpabasic.ex1hellojpa.domain;

import com.jpabasic.ex1hellojpa.hellojpa.BaseEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    @Embedded
    private Address address;
    private DeliveryStatus delieveryStatus;
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getDelieveryStatus() {
        return delieveryStatus;
    }

    public void setDelieveryStatus(DeliveryStatus delieveryStatus) {
        this.delieveryStatus = delieveryStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
