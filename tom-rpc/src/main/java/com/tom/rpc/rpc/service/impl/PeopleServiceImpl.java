package com.tom.rpc.rpc.service.impl;


import com.tom.rpc.core.entity.People;
import com.tom.rpc.api.service.PeopleService;
import com.tom.rpc.rpc.base.BaseRepository;
import com.tom.rpc.rpc.base.BaseServiceImpl;
import com.tom.rpc.rpc.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: tom.tang
 * @date: 2018/4/15
 * @since: 2018/4/15
 * @email: tom.tang@sainstore.com
 * @description:
 */
@Transactional
@Service("peopleService")
@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class PeopleServiceImpl extends BaseServiceImpl<People, Long> implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public BaseRepository<People, Long> getRepository() {
        return peopleRepository;
    }
}
