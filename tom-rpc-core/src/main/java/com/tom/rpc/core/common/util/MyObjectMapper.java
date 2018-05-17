package com.tom.rpc.core.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author tom.tang
 * @date 2018/4/16
 * @email tom.tang@sainstore.com
 * @description 解决Long型传到前端会丢失精度的问题。
 * @since 2018/4/16
 */
public class MyObjectMapper extends ObjectMapper {

    public MyObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);
    }

}
