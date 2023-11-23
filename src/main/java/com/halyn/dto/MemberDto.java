package com.halyn.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String id;
    private String loginId;
    private String password;
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String email;
    private String joinDate;
}
