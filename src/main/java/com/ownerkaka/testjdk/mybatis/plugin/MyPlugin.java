package com.ownerkaka.testjdk.mybatis.plugin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author akun
 * @since 2019-07-14
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
public class MyPlugin implements Interceptor {

    @Getter
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        log.info("intercept target:{} method:{} args:{}", target.getClass().getSimpleName(), method.getName(), args);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        log.info("plugin target:{} wrap:{}", target.getClass().getSimpleName(), wrap.getClass().getSimpleName());
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("properties:{}", properties.toString());
        this.properties = properties;
    }
}