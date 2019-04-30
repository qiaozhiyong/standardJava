package com.qiaozhy.standardjava;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/30 10:15 AM
 */
public class MapTest {
    @Test
    public void test1(){
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.forEach((a,b)-> System.out.println(a+"的值为"+b));


    }
}
