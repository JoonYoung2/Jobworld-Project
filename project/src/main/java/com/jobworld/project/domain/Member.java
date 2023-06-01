package com.jobworld.project.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="jwUserInfo")
@Getter @Setter
public class Member {

    @Id
    @Column(name="userId")
    private String id;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Resume resume;
    
    @Column(name="userPw")
    private String pw;

    @Column(name="userNm")
    private String name;
    
    @Column(name="userBirthday")
    private String birthday;
    
    @Column(name="userEmail")
    private String email;
    
    @Column(name="userPhoneNum")
    private String phoneNum;
    
    private String zip_cd;
    
    private String address_info;
    
    private String address_detail;
    
    private int login_type;
}