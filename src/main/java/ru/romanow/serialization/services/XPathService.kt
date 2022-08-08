package ru.romanow.serialization.services;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public final class XPathService {

    @SneakyThrows
    public static Object findByXPath(String xml, String pathToFind) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(new InputSource(new StringReader(xml)));
        final XPathFactory xPathfactory = XPathFactory.newInstance();
        final XPath xpath = xPathfactory.newXPath();
        final XPathExpression expr = xpath.compile(pathToFind);
        return expr.evaluate(document, XPathConstants.STRING);
    }
}
