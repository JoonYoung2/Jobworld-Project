package com.jobworld.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="jwUserInfo")
@Getter @Setter
public class UserDomain {

    @Id
    @Column(name="user_id")
    private String id;

    @Column(name="user_pw")
    private String pw;

    @Column(name="user_nm")
    private String name;
    
    @Column(name="user_birthday")
    private String birthday;
    
    @Column(name="user_email")
    private String email;
    
    @Column(name="user_phoneNum")
    private String phoneNum;
    
    private String zip_cd;
    
    private String address_info;
    
    private String address_detail;
    
    private int loginType;
}