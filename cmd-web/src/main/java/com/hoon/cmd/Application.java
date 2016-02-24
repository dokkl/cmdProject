package com.hoon.cmd;

import com.hoon.cmd.configuration.CmdDomainApplicationContextConfig;
import com.hoon.cmd.domain.admin.Authority;
import com.hoon.cmd.domain.admin.AuthorityRepository;
import com.hoon.cmd.domain.admin.User;
import com.hoon.cmd.domain.admin.UserRepository;
import com.hoon.cmd.domain.hello.Hello;
import com.hoon.cmd.domain.hello.HelloRepository;
import com.hoon.cmd.sample.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by hoon on 2015-07-21.
 */
//@EnableScheduling
//@EnableJpaRepositories(basePackages = "com.hoon.appting.repository")
@SpringBootApplication
@ComponentScan(basePackages={"com.hoon.cmd"})
@Import({CmdDomainApplicationContextConfig.class})
@EnableAutoConfiguration
//@PropertySource("application-local.properties")
public class Application extends WebMvcConfigurerAdapter {
    static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.debug("==[Application main method start]==");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        /*
        //ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).web(false).run(args);

        createTestDataMember(context);
        */
        //createTestDataSosi(context);
        //createTestHelloData(context);
        //createUserData(context);

        /*String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        log.debug("[========= loaded beanName ==============");
        for (String beanName : beanNames) {
            log.debug("beanName ==> " + beanName);
        }
        log.debug("========= loaded beanName ==============]");*/
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(csrfTokenAddingInterceptor());
        registry.addInterceptor(CmdInterceptor());
    }

    @Bean
    public HandlerInterceptor csrfTokenAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
                if (token != null && modelAndView != null) {
                    log.debug(">> modelAndView : " + modelAndView);
                    log.debug(">> token : " + token);
                    modelAndView.addObject(token.getParameterName(), token);
                } else {
                    log.debug(">> token null");
                }
            }
        };
    }

    @Bean
    protected HandlerInterceptor CmdInterceptor() {
        CmdInterceptor cmdInterceptor = new CmdInterceptor();
        cmdInterceptor.setCacheSeconds(0);
        cmdInterceptor.setUseExpiresHeader(true);
        cmdInterceptor.setUseCacheControlHeader(true);
        cmdInterceptor.setUseCacheControlNoStore(true);
        return cmdInterceptor;
    }

    public static void createTestDataMember(ConfigurableApplicationContext context) {
        MemberRepository memberRepository = context.getBean(MemberRepository.class);

        Member member1 = new Member();
        member1.setMail("dokkl@naver.com");
        member1.setPassword("232323");
        member1.setName("남궁훈");
        member1.setSex(Sex.M);
        member1.setPhoneAuth("01067889629");
        member1.setBirthday("19770507");
        member1.setDeviceRegId("APA91bEK0Hs77VI0G8OmcxOTd1tfFJ5Rph3W4S5te9zdlXySkPkPXUk2kcm9eWNbT1xp-EAllQnwcTZ8cF7gYepjQkMomqJqOifg6Ixfs0VAI5KF2eWVELV7YxaX0yHDD-Je8TuKp6EcHrDhCsB3vF7XCY6hvB_w-Q");
        member1.setPhoneAuth("502823");
        member1.setAge(38);
        member1.setAddress1("서울");
        member1.setJob("IT.인터넷");
        member1.setHeight("170");
        member1.setReligion("기독교");
        member1.setHobby("운동.스포츠,TV.인터넷,");
        member1.setImage1("dokkl_1439138926135.jpg");
        member1.setImage2("dokkl_1438955643581.jpg");
        member1.setBloodType("B형");
        member1.setBodyType("근육질");
        member1.setCharacterType("내성적인, 친철한");
        member1.setCreateAt(new Date());
        member1.setUpdateAt(new Date());
        member1.setNickName("먹튀");

        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setMail("juju@naver.com");
        member2.setPassword("123456");
        member2.setName("김봉숙");
        member2.setSex(Sex.F);
        member2.setPhoneAuth("01067881234");
        member2.setBirthday("19801107");
        member2.setDeviceRegId("APA91bEO2-GKYZ2igNBNGz5hdfYuCxUEiDez7rG1AVpy3mkzomYr1bTidBvLSOFmqWupsOpOqZaHahROKZJtmpIFvuQz1iMk7A3PqeYqmECr6yUb7JWNtO-UMX4y87LPqhS1fuGSC1GW3-KSi1rBOaMu8Z3OQz6x6A");
        member2.setPhoneAuth("349085");
        member2.setAge(35);
        member2.setAddress1("서울");
        member2.setJob("IT.인터넷");
        member2.setHeight("170");
        member2.setReligion("불교");
        member2.setHobby("운동.스포츠,TV.인터넷,");
        member2.setImage1("smile_1438956302989.jpg");
        member2.setImage2("smile_1438956339800.jpg");
        member2.setBloodType("B형");
        member2.setBodyType("뚱뚱한");
        member2.setCharacterType("지적인, 친철한");
        member2.setCreateAt(new Date());
        member2.setUpdateAt(new Date());
        member2.setNickName("꾸미");

        memberRepository.save(member2);

        log.debug("member1 sex ==> " + member1.getSex().getSexType() + ":" + member1.getSex().name());
        log.debug("member2 sex ==> " + member2.getSex().getSexType() + ":" + member2.getSex().name());
    }

    private static void createTestDataSosi(ConfigurableApplicationContext context) {
        SosiRepository sosiRepository = context.getBean(SosiRepository.class);
        sosiRepository.save(new Sosi("태연"));
        sosiRepository.save(new Sosi("윤아"));
        sosiRepository.save(new Sosi("수영"));
        sosiRepository.save(new Sosi("효연"));
        sosiRepository.save(new Sosi("유리"));
        sosiRepository.save(new Sosi("티파니"));
        sosiRepository.save(new Sosi("써니"));
        sosiRepository.save(new Sosi("서현"));
    }

    private static void createTestHelloData(ConfigurableApplicationContext context) {
        HelloRepository helloRepository = context.getBean(HelloRepository.class);
        helloRepository.save(new Hello("안녕하세요"));
        helloRepository.save(new Hello("곤니치와"));
        helloRepository.save(new Hello("세세 자이지엔"));
        helloRepository.save(new Hello("hello"));
    }

    private static void createUserData(ConfigurableApplicationContext context) {
        UserRepository userRepository = context.getBean(UserRepository.class);
        AuthorityRepository authorityRepository = context.getBean(AuthorityRepository.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");
        Authority savedAuthority = authorityRepository.save(authority);

        User user = new User();
        user.setUsername("dokkl");
        user.setEncodedPassword(encoder.encode("2323"));
        user.setNick("babybong");

        Authority authority2 = new Authority();
        authority2.setAuthority("ROLE_USER");
        Authority savedAuthority2 = authorityRepository.save(authority2);

        User user2 = new User();
        user2.setUsername("hoon");
        user2.setEncodedPassword(encoder.encode("2323"));
        user2.setNick("jordan");

        user.setAuthority(savedAuthority);
        user2.setAuthority(savedAuthority2);
        userRepository.save(user);
        userRepository.save(user2);
    }

}
