package com.qiaozhy.standardjava.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaozhy.standardjava.DTO.UserDTO;
import com.qiaozhy.standardjava.entity.User;
import com.qiaozhy.standardjava.service.UserService;
import com.qiaozhy.standardjava.util.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:59 PM
 */
@RequestMapping("/v1/api/user")
@RestController
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private Validator validator ;

    @PostMapping
    public UserDTO addUser(UserDTO userDTO){
        BeanValidators.validateWithException(validator, userDTO);
        User user =  userDTO.convertToUser();
        User saveResultUser = userService.addUser(user);
        UserDTO result = UserDTO.convertFor(saveResultUser);
        return result;
    }
    @PostMapping
    public UserDTO findById(Integer uid){
        List<String> list = Lists.newArrayList();
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        User user = new User().setName("adfa").setId(11);
        UserDTO result = UserDTO.convertFor(user);
        return result;
    }

}
