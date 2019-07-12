package com.ownerkaka.testjdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author akun
 * @since 2019-07-12
 */
@Slf4j
public class XPathTests {


    private String xml = "<?xml version=\"1.0\"?>\n" +
            "<class>\n" +
            "   <student rollno=\"393\">\n" +
            "      <firstname>dinkar</firstname>\n" +
            "      <lastname>kad</lastname>\n" +
            "      <nickname>dinkar</nickname>\n" +
            "      <marks>85</marks>\n" +
            "   </student>\n" +
            "   <student rollno=\"493\">\n" +
            "      <firstname>Vaneet</firstname>\n" +
            "      <lastname>Gupta</lastname>\n" +
            "      <nickname>vinni</nickname>\n" +
            "      <marks>95</marks>\n" +
            "   </student>\n" +
            "   <student rollno=\"593\">\n" +
            "      <firstname>jasvir</firstname>\n" +
            "      <lastname>singh</lastname>\n" +
            "      <nickname>jazz</nickname>\n" +
            "      <marks>90</marks>\n" +
            "   </student>\n" +
            "</class>";


    @Test
    public void testXPath() throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/class/student";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(createDocument(), XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element element = (Element) item;
            String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
            log.info("firstname:{}", firstname);
        }
    }

    private Document createDocument() {
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            return documentBuilder.parse(input);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}