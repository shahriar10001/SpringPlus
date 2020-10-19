package Dao.Config;

import DaoEntity.HibernateMappingClass;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:hibernateConfig.properties"})
@ComponentScan({"Dao.Config"})

public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException, IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("Dao.Entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setAnnotatedClasses(HibernateMappingClass.initialize());
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setConnectionCachingEnabled(Boolean.parseBoolean(environment.getProperty("hibernate.setConnectionCachingEnabled")));
//        dataSource.setConnectionCacheName("ImplicitCache01");

        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setUser(environment.getProperty("hibernate.username"));
        dataSource.setPassword(environment.getProperty("hibernate.password"));
        dataSource.setURL(environment.getProperty("hibernate.url"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("MinLimit", "2");
        properties.setProperty("MaxLimit", "10");
        properties.setProperty("hibernate.show_sql", "hibernate.show_sql");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
