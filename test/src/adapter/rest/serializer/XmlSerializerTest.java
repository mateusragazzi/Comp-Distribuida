package src.adapter.rest.serializer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.domain.entity.Actor;

class XmlSerializerTest {

    @Test
    void shouldSerializeAnObjectToXmlString() {
        Actor actor = new Actor("john", "12/12/2000");
        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<actor>\n" +
                "    <name>john</name>\n" +
                "    <birthdate>12/12/2000</birthdate>\n" +
                "</actor>\n";

        String actualXml = new XmlSerializer().serialize(actor);

        Assertions.assertEquals(expectedXml, actualXml);
    }
}