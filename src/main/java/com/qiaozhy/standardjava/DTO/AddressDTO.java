package com.qiaozhy.standardjava.DTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:38 PM
 */
@Data
public class AddressDTO {
    @NotNull
    private String province;//省
    @NotNull
    private String city;//市
    @NotNull
    private String county;//区

    private Boolean isDefault;//是否是默认地址

    private  Integer uid;
}
