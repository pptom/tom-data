package com.tom.rpc.rpc.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/13
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaSpecificationExecutor<T>,JpaRepository<T, ID> {
}