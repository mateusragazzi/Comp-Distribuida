package src.domain.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "movie")
public class Movie {
    private Long ID;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "synopsis")
    private String synopsis;

    public Movie() {
    }

    public Movie(Long ID, String title, String synopsis) {
        this.title = title;
        this.synopsis = synopsis;
    }

    public Movie(String title, String synopsis) {
        this.title = title;
        this.synopsis = synopsis;
    }

    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @XmlAttribute
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(synopsis, movie.synopsis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, synopsis);
    }
}
