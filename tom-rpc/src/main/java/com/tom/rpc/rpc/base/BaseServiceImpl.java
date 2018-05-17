package com.tom.rpc.rpc.base;




import com.tom.rpc.core.entity.base.BaseEntity;
import com.tom.rpc.api.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/13
 */
@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseRepository<T, ID> getRepository();

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T saveAndFlush(T t) {
        t = getRepository().save(t);
        getRepository().flush();
        return t;
    }

    @Override
    public T update(T t) {
        return getRepository().save(t);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Override
    public void delete(T t) {
        getRepository().delete(t);
    }

    @Override
    public void delete(Iterable<T> entities) {
        getRepository().delete(entities);
    }

    @Override
    public void deleteAll(){
        getRepository().deleteAll();
    }

    @Override
    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return getRepository().exists(id);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public List<T> list(Map<String, Object> params) {
        Specification<T> spec = getSpecification(params);
        List<T> list = getRepository().findAll(spec);
        return list;
    }

    @Override
    public Page<T> list(Map<String, Object> params, Pageable pageable) {
        Specification<T> spec = getSpecification(params);
        Page<T> page = getRepository().findAll(spec, pageable);
        return page;
    }

    /**
     * 创建动态查询
     *
     * @param params
     * @return
     */
    private Specification<T> getSpecification(Map<String, Object> params) {
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<T> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            //遍历参数
            for (Entry<String, SearchFilter> entry : filters.entrySet()) {
                SearchFilter searchFilter = entry.getValue();
                String fieldName = searchFilter.getFieldName();
                OperatorEnum operatorEnum = searchFilter.getOperatorEnum();
                Object value = searchFilter.getValue();
                //转换成查询条件
                Predicate predicate = getPredicate(fieldName, operatorEnum, value, root, cb);
                list.add(predicate);
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        return specification;
    }


    /**
     * 根据操作符转换成Predicate
     *
     * @param fieldName
     * @param operatorEnum
     * @param value
     * @param root
     * @param cb
     * @return
     */
    private Predicate getPredicate(String fieldName, OperatorEnum operatorEnum, Object value, Root<T> root
            , CriteriaBuilder cb) {
        if (operatorEnum == OperatorEnum.EQ) {
            return cb.equal(root.get(fieldName).as(value.getClass()), value);
        }
        if (operatorEnum == OperatorEnum.NE) {
            return cb.notEqual(root.get(fieldName).as(value.getClass()), value);
        }
        if (operatorEnum == OperatorEnum.LIKE) {
            return cb.like(root.get(fieldName).as(String.class), String.format("%%%s%%", value));
        }
        if (operatorEnum == OperatorEnum.LT) {
            return getLessThanPredicate(fieldName, value, root, cb);
        }
        if (operatorEnum == OperatorEnum.LTE) {
            return getLessThanOrEqualToPredicate(fieldName, value, root, cb);
        }
        if (operatorEnum == OperatorEnum.GT) {
            return getGreaterThanPredicate(fieldName, value, root, cb);
        }
        if (operatorEnum == OperatorEnum.GTE) {
            return getGreaterThanOrEqualToPredicate(fieldName, value, root, cb);
        }
        //遇到不支持的已经在SearchFilter.parse抛出异常
        return null;
    }


    private Predicate getLessThanPredicate(String fieldName, Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Float.class), (float) value);
        }
        if (Timestamp.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Timestamp.class), (Timestamp) value);
        }
        if (Date.class == value.getClass()) {
            return cb.lessThan(root.get(fieldName).as(Date.class), (Date) value);
        }
        return cb.lessThan(root.get(fieldName).as(String.class), String.valueOf(value));
    }

    private Predicate getLessThanOrEqualToPredicate(String fieldName, Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Float.class), (float) value);
        }
        if (Timestamp.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Timestamp.class), (Timestamp) value);
        }
        if (Date.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(fieldName).as(Date.class), (Date) value);
        }
        return cb.lessThanOrEqualTo(root.get(fieldName).as(String.class), String.valueOf(value));
    }

    private Predicate getGreaterThanPredicate(String fieldName, Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Float.class), (float) value);
        }
        if (Timestamp.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Timestamp.class), (Timestamp) value);
        }
        if (Date.class == value.getClass()) {
            return cb.greaterThan(root.get(fieldName).as(Date.class), (Date) value);
        }
        return cb.greaterThan(root.get(fieldName).as(String.class), String.valueOf(value));
    }

    private Predicate getGreaterThanOrEqualToPredicate(String fieldName, Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Float.class), (float) value);
        }
        if (Timestamp.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Timestamp.class), (Timestamp) value);
        }
        if (Date.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(fieldName).as(Date.class), (Date) value);
        }
        return cb.lessThanOrEqualTo(root.get(fieldName).as(String.class), String.valueOf(value));
    }
}
