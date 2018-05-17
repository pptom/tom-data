package com.tom.rpc.rpc.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tom.tang
 * @date 2018/4/11
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/11
 */
@Configuration
@DubboComponentScan(basePackages = "com.tom.rpc.rpc.service.impl")
public class DubboConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(DubboConfiguration.class);

    @Bean
    @ConfigurationProperties(prefix = "dubbo.application")
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.registry")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.protocol")
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        return protocolConfig;
    }
}
