package com.example.cracdbrefreshscope.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.HikariCheckpointRestoreLifecycle;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DataSourceConfig {

	@Value("${password}")
	String password;

	@Bean
	@RefreshScope
	public DataSource dataSource() {
		DataSourceBuilder<HikariDataSource> dataSourceBuilder = DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/database")
				.username("user")
				.password(password);
		HikariDataSource dataSource = dataSourceBuilder.build();
		dataSource.setAllowPoolSuspension(true);
		return dataSource;
	}

	// Instantiating HikariCheckpointRestoreLifecycle for user-provided DataSource beans
	// will no longer be necessary after https://github.com/spring-projects/spring-boot/pull/37630
	// has been merged
	@Bean
	public HikariCheckpointRestoreLifecycle checkpointRestoreLifecycle(DataSource dataSource){
		return new HikariCheckpointRestoreLifecycle(dataSource);
	}
}
