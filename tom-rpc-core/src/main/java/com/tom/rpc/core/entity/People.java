package com.tom.rpc.core.entity;



import com.tom.rpc.core.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: tom.tang
 * @date: 2018/4/15
 * @since: 2018/4/15
 * @email: tom.tang@sainstore.com
 * @description:
 */
@Entity
public class People extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = -4621843737458224695L;
    private String name;
    private Integer age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
