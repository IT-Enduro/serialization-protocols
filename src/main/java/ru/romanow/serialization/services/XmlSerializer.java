package ru.romanow.serialization.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.romanow.serialization.model.xml.XmlTestObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by ronin on 09.09.16
 */
public class XmlSerializer {
    private static final Logger logger = LoggerFactory.getLogger(XmlSerializer.class);

    public static String toXml(Object object) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(XmlTestObject.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(object, writer);
            result = writer.toString();
        } catch (JAXBException exception) {
            logger.error("", exception);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(String xml, Class<T> cls) {
        T result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(XmlTestObject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(xml);
            result = (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException exception) {
            logger.error("", exception);
        }

        return result;
    }
}
