package src.domain.entity;

public class Movie {
    private Long ID;
    private String title;
    private String synopsis;

    public Movie(String title, String synopsis) {
        this.title = title;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
