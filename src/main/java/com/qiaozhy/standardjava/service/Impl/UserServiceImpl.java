package com.qiaozhy.standardjava.service.Impl;

import com.google.common.collect.Lists;
import com.qiaozhy.standardjava.entity.User;
import com.qiaozhy.standardjava.repository.IUserDao;
import com.qiaozhy.standardjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 8:00 PM
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private IUserDao userDao;

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public List<User> listUser() {
        List<User> userList = userDao.findAll();
        if(CollectionUtils.isEmpty(userList)){
            return Lists.newArrayList();//guava类库提供的方式
        }
        return userList;
    }
    @Override
    public Optional<User> findById(Integer id){
        return userDao.findById(id);
    }
}
