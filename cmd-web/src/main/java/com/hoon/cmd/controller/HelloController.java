package com.hoon.cmd.controller;

import com.hoon.cmd.domain.hello.Hello;
import com.hoon.cmd.domain.hello.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hoon on 2015-10-16.
 */
@Controller
@Slf4j
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Value("${image.home}")
    private String imageHome;

    @RequestMapping(value = "/hello/{id}")
    public String hello(Model model, @PathVariable Long id) {
        log.debug("속성파일 읽기 imageHome!! : " + imageHome);
        log.debug("id!! : " + id);
        Hello hello = helloService.findHello(id);
        log.debug("hello id : " + hello.getId());
        log.debug("hello name : " + hello.getName());
        model.addAttribute("hello", hello);

        List<Hello> helloList = helloService.findByIdLimit();
        for (Hello hello1 : helloList) {
            log.debug("hello : [" + hello1.getId() + "]" + hello1.getName());
        }
        return "hello";
    }
}
