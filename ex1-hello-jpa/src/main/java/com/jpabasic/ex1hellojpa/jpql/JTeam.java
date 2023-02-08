package com.jpabasic.ex1hellojpa.jpql;

import com.jpabasic.ex1hellojpa.hellojpa.Team;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JTeam {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<JMember> members = new ArrayList<>();

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

    public List<JMember> getMembers() {
        return members;
    }

    public void setMembers(List<JMember> members) {
        this.members = members;
    }
}
