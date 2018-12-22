package com.epam.audiospot.entity;

public class Playlist implements Entity {
    private Long id;

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
