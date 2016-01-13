package com.hoon.cmd.domain.hello;

import java.util.List;

/**
 * Created by hoon on 2015-10-21.
 */
public interface HelloService {
    Hello findHello(Long id);

    List<Hello> findByIdLimit();
}
