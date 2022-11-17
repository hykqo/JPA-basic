package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "HELLO_ITEM")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class HelloItem {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
