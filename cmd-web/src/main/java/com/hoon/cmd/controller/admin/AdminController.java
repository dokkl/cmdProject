package com.hoon.cmd.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hoon on 2016-02-10.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/authority")
    public String authority() {
        return "admin/authority";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }
}
