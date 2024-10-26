package org.primepro.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages ="org.primepro" )
public class AppConfig {
	
	
	
	@Bean
  public DataSource dataSource() // datasource bean to connect with database
  {
	  DriverManagerDataSource dataSource=new DriverManagerDataSource();//jdbc provide implementation class
	  dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	  dataSource.setUrl("jdbc:mysql://localhost:3306/primepro");
	  dataSource.setUsername("root");
	  dataSource.setPassword("root");
	  return dataSource;
  }
	//inject DataSource bean in JDBC template bean,JDBCTemplate is a class
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
	
}
