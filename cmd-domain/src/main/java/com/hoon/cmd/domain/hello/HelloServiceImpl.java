package com.hoon.cmd.domain.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hoon on 2015-10-21.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Value("${spring.jpa.database}")
    private String db;

    @Override
    public Hello selectHello() {
        Hello hello = new Hello();
        hello.setId(12001L);
        hello.setName("안녕하세요? db : " + db);
        return hello;
    }
}
