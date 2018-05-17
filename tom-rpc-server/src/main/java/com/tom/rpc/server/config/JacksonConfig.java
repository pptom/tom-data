package com.tom.rpc.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.rpc.core.common.util.MyObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tom.tang
 * @date 2018/4/16
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/16
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new MyObjectMapper();
    }
}
