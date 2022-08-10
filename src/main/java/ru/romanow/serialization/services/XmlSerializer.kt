package ru.romanow.serialization.services

import ru.romanow.serialization.model.XmlTestObject
import java.io.StringReader
import java.io.StringWriter
import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.UnmarshalException
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

private const val XSD_SCHEMA_FILE = "/data/data.xsd"

fun toXml(data: Any): String {
    val context = JAXBContext.newInstance(XmlTestObject::class.java)
    val marshaller = context.createMarshaller()
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    val writer = StringWriter()
    marshaller.marshal(data, writer)
    return writer.toString()
}

@Suppress("UNCHECKED_CAST")
fun <T> fromXml(xml: String): T {
    val context = JAXBContext.newInstance(XmlTestObject::class.java)
    val unmarshaller = context.createUnmarshaller()
    val reader = StringReader(xml)
    return unmarshaller.unmarshal(reader) as T
}

fun validate(xml: String): Boolean {
    var valid = true
    try {
        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        object {}.javaClass.getResource(XSD_SCHEMA_FILE)!!.openStream()
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