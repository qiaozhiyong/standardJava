package com.qiaozhy.standardjava.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaozhy.standardjava.DTO.UserDTO;
import com.qiaozhy.standardjava.config.aspect.WebLog;
import com.qiaozhy.standardjava.entity.User;
import com.qiaozhy.standardjava.exception.NotFindUserException;
import com.qiaozhy.standardjava.service.UserService;
import com.qiaozhy.standardjava.util.BeanValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:59 PM
 */
@RequestMapping("/v1/api/user")
@RestController
@Slf4j
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private Validator validator ;

    @PostMapping("/User")
    public UserDTO addUser(UserDTO userDTO){
        BeanValidators.validateWithException(validator, userDTO);
        User user =  userDTO.convertToUser();
        User saveResultUser = userService.addUser(user);
        UserDTO result = UserDTO.convertFor(saveResultUser);
        return result;
    }
    @GetMapping("/User/{uid}")
    @WebLog(desc = "查找用户")
    public UserDTO findById(@PathVariable Integer uid){

        List<String> list = Lists.newArrayList();
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        //User user = new User().setName("adfa").setId(11);
        final Optional<User> optionalUser = userService.findById(uid);
        final UserDTO result ;
        /*if(optionalUser.isPresent()){
            result = UserDTO.convertFor(optionalUser.get());
        }else{
            throw new NotFindUserException("未找到");
        }*/
        optionalUser.orElseThrow( () -> new NotFindUserException("未找到"));
        result = UserDTO.convertFor(optionalUser.get());
        if (log.isDebugEnabled()) {
            log.debug("查找用户 id:[{}] and 结果为 : [{}] ", uid, result);
        }
        return result;
    }



}
