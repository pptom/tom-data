package com.tom.rpc.core.common.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/13
 */
public class SnowflakeGenerator implements IdentifierGenerator {

    public static final String TYPE = "com.tom.rpc.core.common.util.SnowflakeGenerator";

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return IdGenerateFactory.nextId();
    }
}
