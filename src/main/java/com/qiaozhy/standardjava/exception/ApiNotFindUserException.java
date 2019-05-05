package com.qiaozhy.standardjava.exception;


import com.qiaozhy.standardjava.model.BaseAddressErrorCode;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:40 PM
 */
public class ApiNotFindUserException extends ApiException{
    public ApiNotFindUserException(String message) {
        super(BaseAddressErrorCode.NOT_FIND_USER_ERROR_CODE, message, null);
    }
}
