package com.young.sarangbang.model.entity.login.repository;

import com.young.sarangbang.model.entity.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface UserRepository extends JpaRepository <User, Integer>{

}
