package com.epam.audiospot.entity;

public class AudioSet implements Entity {
    private static final long serialVersionUID = 7455159949452216440L;

    public static final String ID_LABEL = "audioset_id";
    public static final String TITLE_LABEL = "title";
    public static final String DESCRIPTION_LABEL = "description";

    private Long id;
    private String title;
    private String description;

    public AudioSet(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AudioSet audioSet = (AudioSet) object;
        return  id.equals(audioSet.getId())
                && title.equals(audioSet.getTitle())
                && description.equals(audioSet.getDescription());
    }

    @Override
    public int hashCode() {
        int result =17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + title.hashCode();
        result = prime * result + description.hashCode();
        return result;
    }

    @Override
    public Long getId() {
        return id;
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
}
