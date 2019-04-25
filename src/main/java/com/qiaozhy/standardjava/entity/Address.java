package com.qiaozhy.standardjava.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:41 PM
 */
@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String province;//省
    @NotNull
    private String city;//市
    @NotNull
    private String county;//区

    private Boolean isDefault;//是否是默认地址



    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="uid")
    private User user;
}
