package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker")
    private HelloMember member;


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

    public HelloMember getMember() {
        return member;
    }

    public void setMember(HelloMember member) {
        this.member = member;
    }

}
