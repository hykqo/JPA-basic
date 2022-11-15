package com.jpabasic.ex1hellojpa.hellojpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HELLO_TEAM")
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    @OneToMany(mappedBy = "team")
    private List<HelloMember> members;

    private String name;

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

    public List<HelloMember> getMembers() {
        return members;
    }

    public void setMembers(List<HelloMember> members) {
        this.members = members;
    }
}
