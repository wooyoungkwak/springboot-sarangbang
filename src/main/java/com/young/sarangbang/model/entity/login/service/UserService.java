package com.young.sarangbang.model.entity.login.service;

import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.entity.login.domain.User;

import java.util.List;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface UserService {

    public User get(Integer userSeq) throws SarangbangException;

    public User get(String userName) throws SarangbangException;

    public User getByDB(String userName) throws SarangbangException;

    public List<User> gets() throws SarangbangException;

    public boolean isUser(String userName, String password) throws SarangbangException;

    public boolean isUser(String userName) throws SarangbangException;

    public void add(User user) throws SarangbangException;

    public void adds(List<User> users) throws SarangbangException;

}
