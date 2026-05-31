package com.conexion.nominacrud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "econoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.econo")
    public DataSource econoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "conexionDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.conexion")
    public DataSource conexionDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("ECONO", econoDataSource());
        targetDataSources.put("CONEXION", conexionDataSource());
        
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        dynamicRoutingDataSource.setDefaultTargetDataSource(econoDataSource()); 
        
        return dynamicRoutingDataSource;
    }
}