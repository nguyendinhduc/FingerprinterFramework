package com.uet.fingerpinter;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.uet.fingerpinter.jooq.ExceptionTranslator;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class InitConfigMysql {
    private static Logger LOG = LoggerFactory.getLogger(InitConfigMysql.class);
    private Environment environment;

    @Autowired
    public InitConfigMysql(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getRequiredProperty("spring.datasource.driverClassName"));
            String url = environment.getRequiredProperty("spring.datasource.url");
            dataSource.setJdbcUrl(url);
            LOG.info("url: " + url);
            String username = environment.getRequiredProperty("spring.datasource.username");
            dataSource.setUser(username);
            LOG.info("username: " + username);
            String password = environment.getRequiredProperty("spring.datasource.password");
            LOG.info("password: " + password);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            LOG.debug(e.getMessage());
        }
        return dataSource;
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource()));
    }

    @Bean(name = "dslContext")
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    @Bean
    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));
//        jooqConfiguration.setSQLDialect(SQLDialect.POSTGRES_9_4);
        jooqConfiguration.setSQLDialect(SQLDialect.MYSQL);
        Settings settings = new Settings();
        settings.setRenderSchema(Boolean.FALSE);
        settings.setRenderCatalog(Boolean.FALSE);
        jooqConfiguration.setSettings(settings);
        return jooqConfiguration;
    }

    @Bean
    public ExceptionTranslator exceptionTransformer() {
        return new ExceptionTranslator();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
