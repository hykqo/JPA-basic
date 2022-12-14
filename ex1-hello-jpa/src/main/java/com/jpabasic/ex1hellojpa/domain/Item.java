package com.jpabasic.ex1hellojpa.domain;

import com.jpabasic.ex1hellojpa.hellojpa.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name ="ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuality;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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
