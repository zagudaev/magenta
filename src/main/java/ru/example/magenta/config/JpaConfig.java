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

@Configuration
@EnableJpaRepositories(basePackages = "ru")
@EnableTransactionManagement
public class JpaConfig {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    String dialect;
    @Value("${spring.jpa.show-sql}")
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

       HashMap<String, String> hibernateProperties = new HashMap<>();
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
       hibernateProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
       hibernateProperties.put("hibernate.connection.driver_class", driverClassName);
       hibernateProperties.put("hibernate.dialect", dialect);
       hibernateProperties.put("hibernate.show_sql", showSql);

       HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
       adapter.setGenerateDdl(true);
       LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
       factory.setJpaVendorAdapter(adapter);
       factory.setPackagesToScan("ru");
       factory.setDataSource(dataSource());
       factory.setJpaPropertyMap(hibernateProperties);
       return factory;
   }


}
