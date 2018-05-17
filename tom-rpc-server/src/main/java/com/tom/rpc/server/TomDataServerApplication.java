package com.tom.rpc.server;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author tom.tang
 * @date 2018/4/12
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/12
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.tom.rpc.api.service")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class TomDataServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomDataServerApplication.class, args);
    }
}
