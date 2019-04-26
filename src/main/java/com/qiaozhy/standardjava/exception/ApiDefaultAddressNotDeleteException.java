package com.qiaozhy.standardjava.exception;

import com.qiaozhy.standardjava.model.AddressErrorCode;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:33 PM
 */
public class ApiDefaultAddressNotDeleteException extends ApiException {

    public ApiDefaultAddressNotDeleteException(String message) {
        super(AddressErrorCode.DefaultAddressNotDeleteErrorCode, message, null);
    }
}