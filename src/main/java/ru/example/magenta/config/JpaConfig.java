package ru.example.magenta.config;

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
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "ru")
@EnableTransactionManagement
public class JpaConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    @Value("${spring.jpa.show-sql}")
    private String showSql;


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
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("ru");
        factory.setDataSource(dataSource());
        factory.setJpaPropertyMap(getHibernateProperties());

        return factory;
    }

    private Map<String, String> getHibernateProperties() {
        HashMap<String, String> hibernateProperties = new HashMap<>();

        hibernateProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        hibernateProperties.put("hibernate.connection.driver_class", driverClassName);
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.show_sql", showSql);

        return hibernateProperties;
    }

}
