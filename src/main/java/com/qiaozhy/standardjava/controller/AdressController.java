package com.qiaozhy.standardjava.controller;

import com.qiaozhy.standardjava.DTO.AddressDTO;
import com.qiaozhy.standardjava.entity.Address;
import com.qiaozhy.standardjava.exception.ApiException;
import com.qiaozhy.standardjava.exception.ApiNotFindUserException;
import com.qiaozhy.standardjava.exception.NotFindUserException;
import com.qiaozhy.standardjava.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:35 PM
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AdressController {
    @Autowired
    private IAddressService addressService;

    /**
     * 添加收货地址
     * @param addressDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public AddressDTO add(@Valid @RequestBody AddressDTO addressDTO){
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO,address);
        Address result;
        try {
            result = addressService.createAddress(addressDTO.getUid(), address);
        }catch (NotFindUserException e){
            throw new ApiNotFindUserException("找不到该用户");
        }catch (Exception e){//未知错误
            throw new ApiException(e);
        }
        AddressDTO resultDTO = new AddressDTO();
        BeanUtils.copyProperties(result,resultDTO);
        resultDTO.setUid(result.getUser().getId());

        return resultDTO;
    }
}
