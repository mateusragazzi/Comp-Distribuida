package src.adapter.rest.serializer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.domain.entity.Actor;
import src.domain.entity.Movie;

class XmlSerializerTest {

    @Test
    void shouldSerializeAnActorObjectToXmlString() {
        String name = "john";
        String birthdate = "12/12/2000";
        Actor actor = new Actor(name, birthdate);
        String expectedXml = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<actor>\n" +
                "    <name>%s</name>\n" +
                "    <birthdate>%s</birthdate>\n" +
                "</actor>\n", name, birthdate);

        String actualXml = new XmlSerializer().serialize(actor);

        Assertions.assertEquals(expectedXml, actualXml);
    }

    @Test
    void shouldSerializeAnMovieObjectToXmlString() {
        String title = "The Lord of The Rings";
        String synopsis = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.";
        Movie movie = new Movie(title, synopsis);
        String expectedXml = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<movie>\n" +
                "    <title>%s</title>\n" +
                "    <synopsis>%s</synopsis>\n" +
                "</movie>\n", title, synopsis);

        String actualXml = new XmlSerializer().serialize(movie);

        Assertions.assertEquals(expectedXml, actualXml);
    }
}