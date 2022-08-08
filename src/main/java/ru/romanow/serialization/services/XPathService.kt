package ru.romanow.serialization.services

import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

fun findByXPath(xml: String, pathToFind: String): Any {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val document = builder.parse(InputSource(StringReader(xml)))
    val xPathFactory = XPathFactory.newInstance()
    val xpath = xPathFactory.newXPath()
    val expr = xpath.compile(pathToFind)
    return expr.evaluate(document, XPathConstants.STRING)
}