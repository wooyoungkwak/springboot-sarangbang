package com.young.sarangbang.controller.error;

import com.young.sarangbang.controller.ExtendsController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Date : 2022-03-06
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Controller
public class ErrorController extends ExtendsController {

    @RequestMapping("/401")
    public ModelAndView _401(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getPath("/401"));
        return mv;
    }

    @RequestMapping("/404")
    public ModelAndView _404(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getPath("/404"));
        return mv;
    }

    @RequestMapping("/500")
    public ModelAndView _500(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getPath("/500"));
        return mv;
    }

}
