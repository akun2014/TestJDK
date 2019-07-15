package com.ownerkaka.testjdk.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @author akun
 * @since 2019-07-15
 */
@Slf4j
public class MybatisWithSpringTests {

    SqlSessionFactory sqlSessionFactory;
    static ApplicationContext applicationContext;

    @BeforeClass
    public static void createIocContainer() {
        //create ioc container
        applicationContext = new ClassPathXmlApplicationContext("application-tx.xml");
    }

    @Before
    public void createSqlSessionFactory() throws Exception {
        String resource = "mybatis/mybatis-config.xml";
        ClassPathResource classPathResource = new ClassPathResource(resource);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(classPathResource);
        sqlSessionFactoryBean.setDataSource(applicationContext.getBean(DataSource.class));
        sqlSessionFactoryBean.setEnvironment("prod");
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
    }

    @Test
    public void testUpdate() throws SQLException {
        //get transaction manager
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class);

        //create transaction definition
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setName("txTest");
        transactionDefinition.setTimeout(2);
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        transactionDefinition.setReadOnly(true);

        //Return a currently active transaction or create a new one,
        // according to the specified propagation behavior.
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            int update = sqlSession.update("options.updateOne", "test4");
            if (update > 1) {
                throw new RuntimeException("tx roll back");
            }
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.warn("", e);
            transactionManager.rollback(transactionStatus);
        } finally {
            log.info("{}", transactionStatus.isCompleted());
        }
    }
}