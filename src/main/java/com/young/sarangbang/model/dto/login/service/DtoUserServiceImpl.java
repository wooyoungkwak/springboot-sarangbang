package com.young.sarangbang.model.dto.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.exception.SarangbangExceptionCode;
import com.young.sarangbang.model.dto.login.domain.DtoUser;
import com.young.sarangbang.model.entity.login.domain.User;
import com.young.sarangbang.model.entity.login.enums.Role;
import com.young.sarangbang.model.entity.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date : 2022-03-23
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DtoUserServiceImpl implements DtoUserService {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Override
    public DtoUser get(Integer userSeq) throws SarangbangException {
        return (DtoUser) convertValue(userService.get(userSeq), DtoUser.class);
    }

    @Override
    public DtoUser get(String userName) throws SarangbangException {
        return (DtoUser) convertValue(userService.get(userName), DtoUser.class);
    }

    @Override
    public void add(DtoUser dtoUser) throws SarangbangException {
        dtoUser.setRole(Role.USER);
        User user = (User) convertValue(dtoUser, User.class);
        userService.add(user);
    }

    // JSON 타입을 이용한 객체 변환
    private Object convertValue(Object object, Class clazz) throws SarangbangException {
        return objectMapper.convertValue(object, clazz);
    }
}
