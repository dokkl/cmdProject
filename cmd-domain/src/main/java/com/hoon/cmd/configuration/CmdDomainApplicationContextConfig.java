package com.hoon.cmd.configuration;

import com.hoon.cmd.domain.Domains;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hoon on 2015-10-21.
 */
@Configuration
@ComponentScan(basePackageClasses = Domains.class)
//@EnableJpaRepositories(basePackages = "com.hoon.appting.repository")
public class CmdDomainApplicationContextConfig {
}
