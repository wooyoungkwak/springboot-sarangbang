package com.young.sarangbang.security;

import com.young.sarangbang.model.entity.login.domain.User;
import com.young.sarangbang.model.entity.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("authentication Provider call ....... account({}) ");

        String account = authentication.getName();                      // ID
        String password = (String) authentication.getCredentials();     // PASSWORD

        User user = (User) userDetailsService.loadUserByUsername(account);

        if( user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException("사용자의 계정을 찾을수 없습니다.");
        }

        if ( !userService.isUser(account, password)) {
            throw new BadCredentialsException("사용자의 계정과 패스워드가 맞지 않습니다.");
        }

        // 권한 정보
        Collection< ? extends GrantedAuthority> authorities = user.getAuthorities();
//        Iterator< ? extends GrantedAuthority> iterator = authorities.iterator();
//        while (iterator.hasNext()) {
//            GrantedAuthority grantedAuthority = iterator.next();
//            log.info(" **** authorities = {}", grantedAuthority.getAuthority());
//        }

        return new UsernamePasswordAuthenticationToken(account, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
