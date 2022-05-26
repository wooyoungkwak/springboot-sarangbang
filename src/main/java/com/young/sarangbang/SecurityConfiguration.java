package com.young.sarangbang;

import com.young.sarangbang.security.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * Date : 2022-03-07
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAuthSuccessHandler userAuthSuccessHandler;

    private final UserAuthFailureHandler userAuthFailureHandler;

    private final UserAuthLogoutSuccessHandler userAuthLogoutSuccessHandler;

//    private final UserAuthenticationProvider userAuthenticationProvider;

    private final UserAuthenticationManager authenticationManager;

//    @Override
//    public void configure(AuthenticationManagerBuilder builder) {
//        log.info("security configure register [AuthenticationManagerBuilder] ");
//
//        builder.authenticationProvider(userAuthenticationProvider);
//    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/resources/**");

        log.info("security configure register [WebSecurity]");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // csrf configuration (not use)
        httpSecurity.csrf().disable();

        // X-Frame-Options configuration (not use)
        httpSecurity.headers().frameOptions().disable();

        // UserAuthenticationProvider instead ..
        httpSecurity.authenticationManager(authenticationManager);

        // security Session policy
        // SessionCreationPolicy.ALWAYS         : 스프링시큐리티가 항상 세션을 생성
        // SessionCreationPolicy.IF_REQUIRED    : 스프링시큐리티가 필요시 생성 (기본)
        // SessionCreationPolicy.NEVER          : 스프링시큐리티가 생성하지않지만, 기존에 존재하면 사용
        // SessionCreationPolicy.STATELESS      : 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음 ( JWT 에 주로 사용)
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        // define authorize
        httpSecurity.authorizeRequests()
            // 페이지 권한 설정
            .antMatchers("/password").permitAll()       // /password url 접속 승인
            .antMatchers("/register").permitAll()       // /register url 접속 승인
            .antMatchers("/login").permitAll()          // /login url 접속 승인
//            .anyRequest().authenticated()
            .and()
                .authorizeRequests().antMatchers("/**").authenticated() // 모든 url 은 인증 (단, 상단의 url(/password, /register, /login, /resource/** ) 은 제외)
                .and()
            .formLogin()
                .loginPage("/login")                        // login 페이지
                .usernameParameter("username")              // id 파라미터
                .passwordParameter("password")              // 패스워드 파라미터
                .loginProcessingUrl("/loginProcess")        // 로그인  Form Action URL
                .successHandler(userAuthSuccessHandler)     // ID/PASSWORD 인증 후 처리 Handler
                .failureHandler(userAuthFailureHandler)     // ID/PASSWORD 인증 실패 후 처리 Handler
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")                                   // logout 페이지
                .logoutSuccessHandler(userAuthLogoutSuccessHandler)     // Logout 후 처리 Handler
                .deleteCookies("JSESSIONID")           // 쿠키 삭제
            .and()
            .exceptionHandling()
                .accessDeniedPage("/404");                  // 인증 오류 발생시 이동할 페이지

        log.info(" security configure register [HttpSecurity] ");
    }

}
