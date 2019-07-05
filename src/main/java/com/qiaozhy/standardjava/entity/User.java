package com.qiaozhy.standardjava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 5:43 PM
 */
@Entity
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;//姓名

    @OneToMany(cascade= CascadeType.ALL,mappedBy="user",fetch = FetchType.LAZY)
    private Set<Address> addresses;
}
