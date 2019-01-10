package com.epam.audiospot.entity;

//TODO remake to set or delete
public class Playlist implements Entity {
    private static final long serialVersionUID = 7455159949452216440L;

    public static final String ID_LABEL = "playlist_id";
    public static final String TITLE_LABEL = "name";
    public static final String DESCRIPTION_LABEL = "description";

    private Long id;
    private String title;
    private String description;

    public Playlist(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static Playlist personal(){
        return new Playlist(null,null,null);
    }

    public static Playlist publicSet(String title,String description){
        return new Playlist(null,title,description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
