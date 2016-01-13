package com.hoon.cmd.configuration;

import com.hoon.cmd.domain.Domains;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Created by hoon on 2015-10-21.
 */
@Configuration
@ComponentScan(basePackageClasses = Domains.class)
@PropertySource({ "classpath:domain-${" + AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME + "}.properties" })
//@EnableJpaRepositories(basePackages = "com.hoon.appting.repository")
public class CmdDomainApplicationContextConfig {
}
