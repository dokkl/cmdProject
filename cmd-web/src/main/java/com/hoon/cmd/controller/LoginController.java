package com.hoon.cmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hoon on 2016-01-18.
 */
@Controller
public class LoginController {
    @RequestMapping("loginForm")
    String loginForm() {
        return "loginForm";
    }
}
