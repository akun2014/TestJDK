package com.ownerkaka.testjdk.mybatis;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.sql.*;
import java.util.Properties;

/**
 * @author akun
 * @since 2019-07-14
 */
@Slf4j
public class JDBCTests {

    private Connection getConnection() throws Exception {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        //step zero load db driver (it's not necessary)
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }


    @Test
    public void testJDBC() throws Exception {
        //step one get connect
        Connection connection = getConnection();
        Assert.assertTrue(connection instanceof ConnectionImpl);
        //step two create prepare statement
        String sql = "select * from t_users where uid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Assert.assertTrue(preparedStatement instanceof ClientPreparedStatement);
        //step three set parameter
        preparedStatement.setInt(1, 1);

        ResultSet resultSet = null;
        try {
//step four execute sql
            resultSet = preparedStatement.executeQuery();
            Assert.assertTrue(resultSet instanceof ResultSetImpl);
            //mapping Object
            while (resultSet.next()) {
                //mapping Object ...
                int uid = resultSet.getInt("uid");
                Assert.assertEquals(1, uid);
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            preparedStatement.close();
            connection.close();
        }
    }

    @Test
    public void testJDBCStatement() throws Exception {
        //step one get connect
        Connection connection = getConnection();
        Assert.assertTrue(connection instanceof ConnectionImpl);
        //step two create statement
        Statement statement = connection.createStatement();
        Assert.assertTrue(statement instanceof StatementImpl);
        //step four execute sql
        String sql = "select * from t_users where uid = 1";
        //step five get the result set
        ResultSet resultSet = statement.executeQuery(sql);
        Assert.assertTrue(resultSet instanceof ResultSetImpl);
        while (resultSet.next()) {
            //mapping Object ...
            int uid = resultSet.getInt("uid");
            Assert.assertEquals(1, uid);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}