package src.adapter.rest.serializer;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

public class XmlSerializer implements Serializer {
    @Override
    public String serialize(Object object) {
        StringWriter sw = new StringWriter();

        JAXB.marshal(object, sw);

        return sw.toString();
    }
}
