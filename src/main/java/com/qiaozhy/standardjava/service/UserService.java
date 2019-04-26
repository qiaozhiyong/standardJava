package com.qiaozhy.standardjava.service;

import com.qiaozhy.standardjava.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 8:00 PM
 */
public interface UserService {

    User addUser(User user);

    List<User> listUser();

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户实体,此实体有可能是缺省值
     */
    Optional<User> findById(@NotNull Integer id);


}
