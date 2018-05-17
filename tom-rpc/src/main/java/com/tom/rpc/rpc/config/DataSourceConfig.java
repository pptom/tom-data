package com.tom.rpc.rpc.config;


import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @author tom.tang
 * @date 2018/4/11
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/11
 */
@Configuration
@EntityScan(basePackages = "com.tom.rpc.core.entity")
public class DataSourceConfig {
    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
        return DruidDataSourceBuilder.create().build();
    }
}
