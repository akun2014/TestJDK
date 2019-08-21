package com.ownerkaka.testjdk.spring.tx;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by akun on 2019/3/6.
 */
@Slf4j
public class TransationManagmentTest {

    @Test
    public void test() {
        //create ioc container
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-tx.xml");


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

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM t_users");
            //cause timeout
//            TimeUnit.SECONDS.sleep(4);
            log.info("{}", JSON.toJSONString(list, true));
            //readOnly
            jdbcTemplate.update("update t_users set logged = logged + 1 where uid = '1'");
            List<Map<String, Object>> list2 = jdbcTemplate.queryForList("SELECT * FROM t_users");
            log.info("{}", JSON.toJSONString(list2, true));
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.warn("", e);
            transactionManager.rollback(transactionStatus);
        } finally {
            log.info("{}", transactionStatus.isCompleted());
        }
    }
}
