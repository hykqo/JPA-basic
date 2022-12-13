package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ADDRESS")
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;
    private Address address;

    public AddressEntity(String old2, String street, String s) {
        this.address = new Address(old2, street, s);
    }


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
}
