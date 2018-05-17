package com.tom.rpc.rpc.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.tom.rpc.api.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author tom.tang
 * @date 2018/4/11
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/11
 */
@Service(timeout = 5000)
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String test() {
        logger.info("dubbo 服务测试!");
        return "hello dubbo";
    }
}
