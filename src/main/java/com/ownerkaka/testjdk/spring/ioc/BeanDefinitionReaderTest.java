package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.spring.IOCBase;
import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.support.bean.PropertyBean;
import com.ownerkaka.testjdk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static org.springframework.util.Assert.notNull;

/**
 * Created by akun on 2019/2/22.
 *
 * @see XmlBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see PropertiesBeanDefinitionReader
 */
@Slf4j
public class BeanDefinitionReaderTest extends IOCBase {

    @Test
    public void testXmlBeanDefinitionReader() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        //combination  step 1、2、3
//        reader.loadBeanDefinitions("application.xml");

        // step:1
        ClassPathResource classPathResource = new ClassPathResource("application.xml");
        InputSource inputSource = new InputSource(classPathResource.getInputStream());
        // step:2
        DocumentLoader documentLoader = new DefaultDocumentLoader();
        Document document = documentLoader.loadDocument(inputSource,
                new ResourceEntityResolver(applicationContext),
                new SimpleSaxErrorHandler(LogFactory.getLog(getClass())),
                XmlValidationModeDetector.VALIDATION_XSD, false);
        // step:3
        reader.registerBeanDefinitions(document, null);


        PropertyBean propertyBean = applicationContext.getBean("propertyBean", PropertyBean.class);
        notNull(propertyBean, "bean should not be null");
        notNull(propertyBean.getUser(), "user should not be null");

        // now we can use ioc container
        User user = (User) applicationContext.getBean("user");
        notNull(user, "ioc bean is null ");
    }

    @Test
    public void testClassPathBeanDefinitionScanner() {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(applicationContext);
        scanner.scan("com.gk.support.bean");

        Bar bar = (Bar) applicationContext.getBean("bar");
        notNull(bar, "ioc bean is null");
    }

    @Test
    public void testPropertiesBeanDefinitionReader() {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("application.properties");

        User user = (User) applicationContext.getBean("user");
        notNull(user, "ioc bean is null");
    }
}
