package com.jpabasic.ex1hellojpa.jpql;

import com.jpabasic.ex1hellojpa.hellojpa.Team;

import javax.persistence.*;
@Entity
public class JMember {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
