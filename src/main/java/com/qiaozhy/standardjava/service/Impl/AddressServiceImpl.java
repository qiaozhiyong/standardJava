package com.qiaozhy.standardjava.service.Impl;

import com.google.common.base.Preconditions;
import com.qiaozhy.standardjava.entity.Address;
import com.qiaozhy.standardjava.entity.User;
import com.qiaozhy.standardjava.exception.NotFindUserException;
import com.qiaozhy.standardjava.repository.IAddressDao;
import com.qiaozhy.standardjava.repository.IUserDao;
import com.qiaozhy.standardjava.service.IAddressService;
import com.qiaozhy.standardjava.util.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Validator;
import java.util.List;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:51 PM
 */
@Service
public class AddressServiceImpl implements IAddressService{

    @Autowired
    private IUserDao userDao;
    @Qualifier("IAddressDao")
    @Autowired
    private IAddressDao addressDao;
    @Autowired
    private Validator validator ;

    @Override
    public Address createAddress(Integer uid, Address address) {
        //============ 以下为约束条件   ==============

        //1.用户id不能为空，且此用户确实是存在的

        Preconditions.checkNotNull(uid);
        // TODO: 2019/4/25 hibernate
        User user = null;

        if(null == user){

            throw new NotFindUserException("找不到当前用户!");

        }

        //2.收货地址的必要字段不能为空

        BeanValidators.validateWithException(validator, address);

        //3.如果用户还没有收货地址，当此收货地址创建时设置成默认收货地址

        if(ObjectUtils.isEmpty(user.getAddresses())){

            address.setIsDefault(true);

        }



        //============ 以下为正常执行的业务逻辑   ==============

        address.setUser(user);

        Address result = addressDao.save(address);

        return result;
    }

    @Override
    public void deleteAddress(Integer uid, Integer aid) {

    }

    @Override
    public List<Address> listAddresses(Integer uid) {
        return null;
    }
}
