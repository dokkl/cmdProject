package com.hoon.cmd.controller;

import com.hoon.cmd.domain.hello.Hello;
import com.hoon.cmd.domain.hello.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hoon on 2015-10-16.
 */
@Controller
@Slf4j
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        log.debug("hello !!");
        Hello hello = helloService.selectHello();
        log.debug("hello id : " + hello.getId());
        log.debug("hello name : " + hello.getName());
        model.addAttribute("hello", hello);
        return "hello";
    }
}
