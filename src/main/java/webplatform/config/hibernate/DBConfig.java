package webplatform.config.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		localSessionFactoryBuilder.addProperties(additionalProperties());
		return localSessionFactoryBuilder.scanPackages("webplatform.model.entity").buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?autoReconnect=true");
		// dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		// dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUrl("jdbc:postgresql://pgsql.singandlearn.com.br:5432/singandlearn?autoReconnect=true");
		dataSource.setUsername("singandlearn");
		dataSource.setPassword("e1s2c333");
		// dataSource.setPassword("PORTAL12");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.id.new_generator_mappings", "false");
		return properties;
	}
}
