package com.jpabasic.ex1hellojpa.jpql;

import com.jpabasic.ex1hellojpa.hellojpa.Team;

import javax.persistence.*;
@Entity
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from JMember m where m.username =:username"
)
public class JMember {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private JTeam team;

    @Enumerated(EnumType.STRING)
    private MemberTYPE memberTYPE;

    public void changeTeam(JTeam team){
        this.team = team;
        team.getMembers().add(this);
    }

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

    public JTeam getTeam() {
        return team;
    }

    public void setTeam(JTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "JMember{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
