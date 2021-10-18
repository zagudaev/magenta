package ru.example.Magenta.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "ru")
@EnableTransactionManagement
public class JpaConfig {
    @Value("${spring.datasource.url")
    String url;
    @Value("${spring.datasource.username")
    String username;
    @Value("${spring.datasource.password")
    String password;
    @Value("${spring.datasource.driver-class-name")
    String driverClassName;
    @Value("${spring.jpa.hibernate.ddl-auto")
    String ddlAuto;
    @Value("${spring.jpa.database-platform")
    String dialect;
    @Value("${spring.jpa.show-sql")
    String showSql;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(Environment.HBM2DDL_AUTO, ddlAuto);
        hibernateProperties.put(Environment.DRIVER, driverClassName);
        hibernateProperties.put(Environment.DIALECT,dialect);
        hibernateProperties.put(Environment.SHOW_SQL,showSql);
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("ru");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties);
        return factory;
    }


}
