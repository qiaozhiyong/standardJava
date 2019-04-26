package com.qiaozhy.standardjava.repository;

import com.qiaozhy.standardjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:46 PM
 */
public interface IUserDao extends JpaRepository<User,Integer> {
    @Override
    Optional<User> findById(Integer integer);
}
