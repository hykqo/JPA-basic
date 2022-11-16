package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;
    private String name;
//    @ManyToMany(mappedBy = "products")
//    private List<HelloMember> members = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProduct;

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
}
