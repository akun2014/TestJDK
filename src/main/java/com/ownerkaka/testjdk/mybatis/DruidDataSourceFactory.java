package com.ownerkaka.testjdk.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author akun
 * @since 2019-07-15
 */
@Slf4j
public class DruidDataSourceFactory implements DataSourceFactory {

    Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setConnectProperties(props);
        return druidDataSource;
    }
}