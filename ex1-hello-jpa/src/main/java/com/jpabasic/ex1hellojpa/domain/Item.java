package com.jpabasic.ex1hellojpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name ="ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuality;

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

    public int getStockQuality() {
        return stockQuality;
    }

    public void setStockQuality(int stockQuality) {
        this.stockQuality = stockQuality;
    }
}
