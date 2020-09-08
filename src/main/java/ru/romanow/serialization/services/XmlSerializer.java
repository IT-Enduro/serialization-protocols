package ru.romanow.serialization.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import ru.romanow.serialization.model.XmlTestObject;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public final class XmlSerializer {
    private static final Logger logger = LoggerFactory.getLogger(XmlSerializer.class);
    private static final String XSD_SCHEMA_FILE = "/data/data.xsd";

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
            try (InputStream stream = new ClassPathResource(XSD_SCHEMA_FILE).getInputStream()) {
                Schema schema = schemaFactory.newSchema(new StreamSource(stream));
                JAXBContext context = JAXBContext.newInstance(XmlTestObject.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                unmarshaller.setSchema(schema);

                StringReader reader = new StringReader(xml);
                unmarshaller.unmarshal(reader);
            }
        } catch (Exception exception) {
            logger.error("", exception);
            valid = false;
        }

        return valid;
    }
}
