package src.domain.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "actor")
public class Actor {
    private Long ID;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "birthdate")
    private String birthdate;

    public Actor() {
    }

    public Actor(Long ID, String name, String birthdate) {
        this.ID = ID;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Actor(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public Long getID() {
        return ID;
    }

    @XmlAttribute
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name) &&
                Objects.equals(birthdate, actor.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate);
    }
}