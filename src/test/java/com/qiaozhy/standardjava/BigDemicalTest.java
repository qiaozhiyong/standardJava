package com.qiaozhy.standardjava;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/7/5 2:53 PM
 */
public class BigDemicalTest {
    @Test
    public void main(){
        //ROUND_HALF_DOWN 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
        //如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
        BigDecimal money =new BigDecimal(123.75);
        money = money.setScale(1,BigDecimal.ROUND_HALF_DOWN);
        String str =money.toString();
        System.out.println("ROUND_HALF_UP:"+str);

        //ROUND_HALF_UP 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。
        //如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同(五舍六入)。
        money =new BigDecimal(123.75);
        money = money.setScale(1,BigDecimal.ROUND_HALF_UP);
        str =money.toString();
        System.out.println("ROUND_HALF_UP:"+str);

        //ROUND_DOWN 直接省略多余的小数 如123.15->123.1
        money =new BigDecimal(123.15);
        money = money.setScale(1,BigDecimal.ROUND_DOWN);
        str =money.toString();
        System.out.println("ROUND_DOWN:"+str);

        //ROUND_UP 直接进位 如123.14->123.2
        money =new BigDecimal(123.14);
        money = money.setScale(1,BigDecimal.ROUND_UP);
        str =money.toString();
        System.out.println("ROUND_UP:"+str);

        //ROUND_CEILING 朝正无穷方向round 如果为正数，行为和round_up一样，如果为负数，行为和round_down一样
        money =new BigDecimal(123.14);
        money = money.setScale(1,BigDecimal.ROUND_CEILING);
        str =money.toString();
        System.out.println("ROUND_CEILING:"+str);

        //ROUND_FLOOR 朝朝负无穷方向round 如果为正数，行为和round_down一样，如果为负数，行为和round_up一样
        money =new BigDecimal(123.14);
        money = money.setScale(1,BigDecimal.ROUND_FLOOR);
        str =money.toString();
        System.out.println("ROUND_FLOOR:"+str);


        //ROUND_HALF_EVEN 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP   ；如果它为偶数，则作   ROUND_HALF_DOWN
        money =new BigDecimal(123.14);
        money = money.setScale(1,BigDecimal.ROUND_HALF_EVEN);
        str =money.toString();
        System.out.println("ROUND_HALF_EVEN:"+str);

    }

}

