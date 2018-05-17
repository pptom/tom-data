package com.tom.rpc.api.service;

import com.tom.rpc.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/13
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {
    /**
     * 新增
     */
    T save(T t);

    /**
     * @param t
     * @return
     */
    T saveAndFlush(T t);

    /**
     * 更新
     */
    T update(T t);

    /**
     * 新增或更新
     * 注意数量不要太大，特别是数据迁移时不要使用该方法
     */
//    Iterable<T> save(Iterable<T> entities);
    <S extends T> List<S> save(Iterable<S> entities);

    /**
     * 根据ID删除
     */
    void delete(ID id);

    /**
     * 根据实体删除
     */
    void delete(T t);

    /**
     * 根据主键删除相应实体
     *
     * @param entities
     */
    void delete(Iterable<T> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * 根据ID查找对象
     */
    T findOne(ID id);


    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    boolean exists(ID id);

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    long count();


    /**
     * 查询所有实体
     *
     * @return
     */
    List<T> findAll();


    /**
     * 按照顺序查询所有实体
     *
     * @param sort
     * @return
     */
    List<T> findAll(Sort sort);


    /**
     * 分页排序获取数据
     * 禁止使用该接口进行count操作
     * Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"id"));
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 多条件查询
     * 注：多个条件间是and关系 & 参数是属性对应的类型 使用时注意避免结果集过大
     *
     * @param params {"username:like":"test"} 键的格式为字段名:过滤方式,过滤方式见{@code OperatorEnum}
     * @return
     */
    List<T> list(Map<String, Object> params);

    /**
     * 分页多条件查询
     * 注：多个条件间是and关系 & 参数是属性对应的类型
     *
     * @param params   {"username:like":"test"} 键的格式为字段名:过滤方式,过滤方式见{@code OperatorEnum}
     * @param pageable 分页信息 new PageRequest(page, size,new Sort(Direction.DESC, "updateTime"))
     * @return
     */
    Page<T> list(Map<String, Object> params, Pageable pageable);
}
