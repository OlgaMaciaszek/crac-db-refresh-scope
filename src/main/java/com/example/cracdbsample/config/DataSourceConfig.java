package com.example.cracdbsample.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration
@RefreshScope
public class DataSourceConfig {

	@Value("${password}")
	String password;

	@Bean
	@RefreshScope
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url("jdbc:postgresql://localhost:5432/database");
		dataSourceBuilder.username("user");
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}
}
