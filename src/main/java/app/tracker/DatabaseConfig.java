package app.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig
{
    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( environment.getProperty( "db.driver" ) );
        dataSource.setUrl( environment.getProperty( "db.url" ) );
        dataSource.setUsername( environment.getProperty( "db.username" ) );
        dataSource.setPassword( environment.getProperty( "db.password" ) );

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource( dataSource );
        entityManagerFactory.setPackagesToScan( environment.getProperty( "entitymanager.packagesToScan" ) );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter( vendorAdapter );

        Properties additionalProperties = new Properties();
        additionalProperties.put( "hibernate.dialect", environment.getProperty( "hibernate.dialect" ) );
        additionalProperties.put( "hibernate.show_sql", environment.getProperty( "hibernate.show_sql" ) );
        additionalProperties.put( "hibernate.hbm2ddl.auto", environment.getProperty( "hibernate.hbm2ddl.auto" ) );
        additionalProperties.put( "hibernate.connection.useUnicode", environment.getProperty( "hibernate.connection.useUnicode" ) );
        additionalProperties.put( "hibernate.connection.characterEncoding", environment.getProperty( "hibernate.connection.characterEncoding" ) );
        additionalProperties.put( "hibernate.connection.CharSet", environment.getProperty( "hibernate.connection.CharSet" ) );

        entityManagerFactory.setJpaProperties( additionalProperties );

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory.getObject() );

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}