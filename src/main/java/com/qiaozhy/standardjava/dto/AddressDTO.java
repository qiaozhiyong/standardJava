package com.qiaozhy.standardjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 7:38 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDTO {
    @NotNull
    private String province;
    @NotNull
    private String city;
    @NotNull
    private String county;

    private Boolean isDefault;

    private  Integer uid;
}
