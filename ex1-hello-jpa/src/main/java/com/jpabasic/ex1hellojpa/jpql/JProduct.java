package com.jpabasic.ex1hellojpa.jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JProduct {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int prioe;
    private int stockAmount;

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

    public int getPrioe() {
        return prioe;
    }

    public void setPrioe(int prioe) {
        this.prioe = prioe;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }
}
