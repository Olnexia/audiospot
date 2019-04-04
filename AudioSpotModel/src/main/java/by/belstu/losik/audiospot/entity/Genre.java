package by.belstu.losik.audiospot.entity;

public enum Genre {
    CLASSIC("classic"),
    POP("pop"),
    RAP("rap"),
    ROCK("rock");

    private String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
