package com.young.sarangbang.model.dto.login.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.dto.login.domain.DtoUser;

/**
 * Date : 2022-03-23
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface DtoUserService {

    DtoUser get(Integer userSeq)  throws SarangbangException;

    DtoUser get(String userName)  throws SarangbangException;

    void add(DtoUser dtoUser) throws SarangbangException;

}
