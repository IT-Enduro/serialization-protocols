package ru.romanow.serialization.services

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import jakarta.xml.bind.JAXBContext
import org.xml.sax.InputSource
import ru.romanow.serialization.model.XmlTestObject
import java.io.StringReader
import java.rmi.UnmarshalException
import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

private const val XSD_SCHEMA_FILE = "/data/data.xsd"

private val mapper = XmlMapper()

fun toXml(data: Any): String = mapper
    .writerWithDefaultPrettyPrinter()
    .writeValueAsString(data)

fun <T> fromXml(xml: String, cls: Class<T>): T =
    mapper.readValue(xml, cls)

fun xpath(xml: String, path: String): String {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val document = builder.parse(InputSource(StringReader(xml)))
    val xPathFactory = XPathFactory.newInstance()
    val xpath = xPathFactory.newXPath()
    val expr = xpath.compile(path)
    return expr.evaluate(document, XPathConstants.STRING) as String
}

fun validate(xml: String): Boolean {
    var valid = true
    try {
        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        object {}.javaClass.getResource(XSD_SCHEMA_FILE)!!
            .openStream()
            .use { stream ->
                val schema = schemaFactory.newSchema(StreamSource(stream))
                val context = JAXBContext.newInstance(XmlTestObject::class.java)
                val unmarshaller = context.createUnmarshaller()
                unmarshaller.schema = schema
                val reader = StringReader(xml)
                unmarshaller.unmarshal(reader)
            }
    } catch (exception: UnmarshalException) {
        valid = false
    }
    return valid
}
