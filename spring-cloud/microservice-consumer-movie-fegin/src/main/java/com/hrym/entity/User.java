package com.hrym.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class User implements Serializable {

    private Long id;

    private String username;

    private String name;

    private Short age;

    private BigDecimal balance;

}
