package com.hoon.cmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by hoon on 2016-01-30.
 */
@Controller
public class MainController {

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        //model.addAttribute("username", principal.getName());
        return "dashboard";
    }

    @RequestMapping("/board")
    public String board() {
        return "board";
    }
}
