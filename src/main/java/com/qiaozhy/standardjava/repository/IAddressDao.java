package com.qiaozhy.standardjava.repository;

import com.qiaozhy.standardjava.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:47 PM
 */
@Repository
public interface IAddressDao extends JpaRepository<Address,Integer> {



}
