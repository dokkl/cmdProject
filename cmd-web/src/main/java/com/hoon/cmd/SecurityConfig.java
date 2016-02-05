package com.hoon.cmd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/static/**", "/ace/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();

        log.debug("HttpSecurity >>>");
        http.authorizeRequests()
                .antMatchers("/hello/**").hasRole("ADMIN")
                .antMatchers("/normal/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/loginForm").permitAll()
                .anyRequest().authenticated();
        http.formLogin().loginProcessingUrl("/login")
                .loginPage("/loginForm")
                //.failureUrl("/loginForm?error")
                .failureUrl("/loginForm")
                .defaultSuccessUrl("/dashboard", true)
                //.defaultSuccessUrl("/hello/1", true)
                .usernameParameter("username").passwordParameter("password")
                .and();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/loginForm");
    }

    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsService userDetailsService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            log.debug("AuthenticationConfiguration init >>");
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }
    }

}
