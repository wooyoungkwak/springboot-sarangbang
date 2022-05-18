package com.young.sarangbang.controller.login;

import com.young.sarangbang.controller.ExtendsController;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.dto.login.domain.DtoUser;
import com.young.sarangbang.model.dto.login.service.DtoUserService;
import com.young.sarangbang.model.entity.login.enums.Role;
import com.young.sarangbang.model.entity.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Date : 2022-03-08
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController extends ExtendsController {

    private final DtoUserService dtoUserService;

    /* GET */

    @GetMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) {
        // 인증 페이지로 이동하기 전 URL 기억
        String header = request.getHeader("home");
        request.getSession().setAttribute("prevPage", header);
        return getPath("/login");
    }

    @GetMapping("/password")
    public String password(Model model, HttpServletRequest request){
        return getPath("/password");
    }

    @GetMapping(value = "/register")
    public String register(Model model, HttpServletRequest request){
        return getPath("/register");
    }


    /* POST */

    @PostMapping(value = "/register")
    @ResponseBody
    public String register_(HttpServletRequest request, @RequestBody DtoUser dtoUser){
        dtoUser.setRole(Role.USER);
        try {
            dtoUserService.add(dtoUser);
        } catch (SarangbangException e) {
            return "/register";
        }
        return "/login";
    }

}
