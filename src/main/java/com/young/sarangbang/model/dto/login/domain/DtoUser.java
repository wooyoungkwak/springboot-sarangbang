package com.young.sarangbang.model.dto.login.domain;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */

import com.young.sarangbang.model.entity.login.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

    private Integer userSeq;

    private String username;    // = account

    private String password;

    @Setter
    private Role role;

    private String firstName;

    private String lastName;

    public DtoUser(String  username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
