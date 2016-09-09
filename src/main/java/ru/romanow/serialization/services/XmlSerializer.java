package ru.romanow.serialization.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.romanow.serialization.model.xml.XmlTestObject;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
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
    public static <T> T fromXml(String xml) {
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

    public static boolean validate(String xml) {
        boolean valid = true;
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(getXSDResource()));

            JAXBContext context = JAXBContext.newInstance(XmlTestObject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);

            StringReader reader = new StringReader(xml);
            unmarshaller.unmarshal(reader);
        } catch (Exception exception) {
            logger.error(exception.getCause().getMessage());
            valid = false;
        }

        return valid;
    }

    private static InputStream getXSDResource() {
        return ClassLoader.class.getResourceAsStream("/xml/data.xsd");
    }
}
