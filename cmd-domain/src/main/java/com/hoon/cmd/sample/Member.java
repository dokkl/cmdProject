package com.hoon.cmd.sample;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoon on 2015-04-26.
 */
@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String phone;

    private String birthday;

    @Column(nullable = true)
    private String deviceRegId;

    @Column(nullable = true)
    private String phoneAuth;

    @Column(nullable = true)
    private Integer age = 0;

    @Column(nullable = true)
    private String kakaoId;

    private String address1;

    @Column(nullable = true)
    private String address2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    //기본사항
    private String nickName;

    @Column(nullable = true)
    private String job;

    @Column(nullable = true)
    private String hobby;

    @Column(nullable = true)
    private String characterType;

    @Column(nullable = true)
    private String religion; //종교

    @Column(nullable = true)
    private String bloodType; //혈액형

    @Column(nullable = true)
    private String height;

    @Column(nullable = true)
    private String bodyType;


    @Column(nullable = true)
    private String selfIntroduction;
    @Column(nullable = true)
    private String idealType;
    @Column(nullable = true)
    private String myAppeal;
    //선택사항
    private String drinkAndSmoke;
    private String wantDate;
    private String firstPoint;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    private Date lastConnectDate;

    private Integer lastConnectCount;

    private boolean pushOk = true;

    private boolean connectOk = true;


}
