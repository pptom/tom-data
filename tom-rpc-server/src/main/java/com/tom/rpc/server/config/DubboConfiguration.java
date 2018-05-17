package com.tom.rpc.server.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tom.tang
 * @date 2018/4/12
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/12
 */
@Configuration
public class DubboConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "dubbo.application")
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.consumer")
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        return consumerConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.registry")//dubbo注册
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }
}
