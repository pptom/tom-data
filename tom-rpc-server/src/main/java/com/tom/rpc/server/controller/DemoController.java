package com.tom.rpc.server.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tom.rpc.api.service.PeopleService;
import com.tom.rpc.api.service.TestService;
import com.tom.rpc.core.entity.People;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tom.tang
 * @date 2018/4/12
 * @email tom.tang@sainstore.com
 * @description
 * @since 2018/4/12
 */
@Api(description = "演示操作相关接口")
@RestController
public class DemoController {

    @Reference
    private TestService testService;

    @Reference
    private PeopleService peopleService;

    @ApiOperation(value="Dubbo例子", notes="")
    @GetMapping("/test")
    public String test() {
        return testService.test();
    }

    @ApiOperation(value="添加People", notes="")
    @GetMapping("/add")
    public Object addPeople() {
        People people = new People();
        people.setAddress("hello");
        people.setAge(10);
        people.setName("tomdd");
        return peopleService.save(people);
    }

    @ApiOperation(value="查找People", notes="")
    @GetMapping("/find")
    public Object addPeople(String id) {
        Map<String, Object> params = new HashMap<>();
        /* 其他查询测试 params中key的格式为FIELDNAME:OPERATOR(操作符忽略大小写，建议使用小写如：username:eq)
        params.put("name:like", name);
        params.put("age:gt", 5);
        params.put("age:gte", 10);
        params.put("age:lt", 15);
        params.put("age:lte", 10);
        params.put("age:ne", 10);
        */
        params.put("age:lte", 8);
        return peopleService.list(params);
    }

}
