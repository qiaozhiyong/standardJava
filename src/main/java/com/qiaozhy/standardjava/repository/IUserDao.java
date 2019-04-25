package com.qiaozhy.standardjava.repository;

import com.qiaozhy.standardjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:46 PM
 */
public interface IUserDao extends JpaRepository<User,Integer> {

}
