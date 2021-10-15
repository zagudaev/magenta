package ru.example.Magenta.config;

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

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/distance-calculator");
        return dataSourceBuilder.build();
    }
       @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HashMap<String, String> hibernateProperties = new HashMap<>();
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.show_sql", "false");
       // hibernateProperties.put("hibernate.generate_statistics", "true");
        hibernateProperties.put("hibernate.connection.charSet", "UTF-8");
       // hibernateProperties.put("hibernate.format_sql", "true");
       // hibernateProperties.put("hibernate.use_sql_comments", "true");
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
