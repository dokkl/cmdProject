package com.hoon.cmd.domain.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hoon on 2015-10-21.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloRepository helloRepository;

    @Value("${spring.jpa.database}")
    private String db;

    @Override
    public Hello findHello(Long id) {
        Hello hello = helloRepository.findOne(id);
        return hello;
    }

    @Override
    public List<Hello> findByIdLimit() {
        return helloRepository.findByIdLimit();
    }
}
