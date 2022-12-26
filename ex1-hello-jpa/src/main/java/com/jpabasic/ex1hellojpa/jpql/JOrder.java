package com.jpabasic.ex1hellojpa.jpql;

import javax.persistence.*;

@Entity
public class JOrder {

    @Id @GeneratedValue
    private Long id;
    private int orderAmount;
    @Embedded
    private JAddress address;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private JProduct jProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public JAddress getAddress() {
        return address;
    }

    public void setAddress(JAddress address) {
        this.address = address;
    }

    public JProduct getjProduct() {
        return jProduct;
    }

    public void setjProduct(JProduct jProduct) {
        this.jProduct = jProduct;
    }
}
