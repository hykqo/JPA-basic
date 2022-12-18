package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "HELLOADDRESS")
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;
    private HelloAddress address;

    public AddressEntity(){}

    public AddressEntity(String old2, String street, String s) {
        this.address = new HelloAddress(old2, street, s);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HelloAddress getAddress() {
        return address;
    }

    public void setAddress(HelloAddress address) {
        this.address = address;
    }
}
