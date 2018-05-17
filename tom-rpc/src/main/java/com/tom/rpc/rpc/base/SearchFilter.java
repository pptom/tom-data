package com.tom.rpc.rpc.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author tom.tang
 * @date 2018/4/13
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/13
 */
public class SearchFilter {

    private String fieldName;
    private OperatorEnum operatorEnum;
    private Object value;

    public String getFieldName() {
        return fieldName;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public Object getValue() {
        return value;
    }

    public SearchFilter(String fieldName, OperatorEnum operatorEnum, Object value) {
        this.fieldName = fieldName;
        this.operatorEnum = operatorEnum;
        this.value = value;
    }

    /**
     * params中key的格式为FIELDNAME:OPERATOR(操作符忽略大小写，建议使用小写如：username:eq)
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> params) {
        Map<String, SearchFilter> filters = new HashMap<>();
        for (Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 过滤掉空值，即不参与查询
            if (value == null) {
                continue;
            }
            // 拆分filedAttribute与operator
            String[] arr = key.split(":");
            String fieldName = arr[0];
            //没有操作符就是等于
            String operatorEnumStr = "eq";
            if (arr.length != 1) {
                operatorEnumStr = arr[1].toUpperCase();
            }
            OperatorEnum operatorEnum = null;
            try {
                operatorEnum = OperatorEnum.valueOf(operatorEnumStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException(String.format("Unsupported operator [%s]", operatorEnum.name()));
            }
            // 创建searchFilter
            SearchFilter filter = new SearchFilter(fieldName, operatorEnum, value);
            filters.put(key, filter);
        }
        return filters;
    }
}
