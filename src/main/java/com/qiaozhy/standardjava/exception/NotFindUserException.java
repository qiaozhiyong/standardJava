package com.qiaozhy.standardjava.exception;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:16 PM
 */
public class NotFindUserException extends RuntimeException {

    public NotFindUserException() {

        super("找不到此用户");

    }



    public NotFindUserException(String message) {

        super(message);

    }

}