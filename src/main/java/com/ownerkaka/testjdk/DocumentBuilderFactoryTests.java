package com.ownerkaka.testjdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author akun
 * @since 2019-06-21
 */
@Slf4j
public class DocumentBuilderFactoryTests {

    @Test
    public void testDocumentBuilderFactory() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        //开启document文档校验
        builderFactory.setValidating(true);
//        builderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
//        documentBuilder.setErrorHandler(null);
//        documentBuilder.setEntityResolver(null);

        ClassPathResource classPathResource = new ClassPathResource("application-test.xml");
        Document document = documentBuilder.parse(classPathResource.getInputStream());

        Element element = document.getDocumentElement();

        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        log.info("{}", length);

        String attribute = element.getAttribute("xmlns");
        log.info("{}", attribute);
    }
}