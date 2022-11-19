package com.jpabasic.ex1hellojpa.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item{

    private String author;
    private String lsbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLsbn() {
        return lsbn;
    }

    public void setLsbn(String lsbn) {
        this.lsbn = lsbn;
    }
}
