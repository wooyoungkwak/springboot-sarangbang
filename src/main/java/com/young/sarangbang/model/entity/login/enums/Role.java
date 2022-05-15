package com.young.sarangbang.model.entity.login.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@AllArgsConstructor
@Getter
public enum Role {

    USER("ROLE_USER"),

    ADMIN("ROLE_ADMIN");

    private String value;

}
