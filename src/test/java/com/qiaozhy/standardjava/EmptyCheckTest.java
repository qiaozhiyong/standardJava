package com.qiaozhy.standardjava;

import com.google.common.base.Preconditions;
import com.qiaozhy.standardjava.entity.Address;
import com.qiaozhy.standardjava.util.BeanValidators;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.validation.Validator;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/7/18 5:30 PM
 */
public class EmptyCheckTest {

    @Autowired
    private Validator validator;

    @Test
    public void test1() {
       /* Object object = new Object();
        // 对象校验空异常
        final boolean empty = ObjectUtils.isEmpty(object);

        // 对象校验为null
        Preconditions.checkNotNull(object);
*/
        Address address = new Address();
        address.setCity("qiao");

        BeanValidators.validateWithException(validator, address);


    }
}
