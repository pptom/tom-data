package com.tom.rpc.core.entity.base;



import com.tom.rpc.core.common.util.SnowflakeGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * @since 2018/4/13
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

    @Id
    @GenericGenerator(name = "snowflake", strategy = SnowflakeGenerator.TYPE)
    @GeneratedValue(generator = "snowflake")
    private ID id;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(final ID id) {
        this.id = id;
    }
}
