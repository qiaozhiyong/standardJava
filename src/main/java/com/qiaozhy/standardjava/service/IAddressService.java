package com.qiaozhy.standardjava.service;

import com.qiaozhy.standardjava.entity.Address;

import java.util.List;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:48 PM
 */
public interface IAddressService {



    /**

     * 创建收货地址

     * @param uid

     * @param address

     * @return

     */

    Address createAddress(Integer uid, Address address);



    /**

     * 删除收货地址

     * @param uid

     * @param aid

     */

    void deleteAddress(Integer uid,Integer aid);



    /**

     * 查询用户的所有收货地址

     * @param uid

     * @return

     */

    List<Address> listAddresses(Integer uid);

}

