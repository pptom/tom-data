package com.tom.rpc.core.entity.base;


import java.io.Serializable;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description 抽象实体基类，请使用{@link BaseEntity}
 * @since 2018/4/13
 */
public abstract class AbstractEntity<ID extends Serializable> {



    public abstract ID getId();

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public abstract void setId(final ID id);


    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        return null != this.getId() && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

}
