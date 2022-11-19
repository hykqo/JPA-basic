package com.jpabasic.ex1hellojpa.hellojpa;

import com.jpabasic.ex1hellojpa.domain.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class HelloAlbum extends HelloItem {
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
