package com.halyn.service;

import com.halyn.dto.MemberDto;

public interface MemberService {
    void insertMember(MemberDto member) throws Exception;
    MemberDto selectMember(String loginId) throws Exception;

    // 아이디 & 비밀번호 << 1. 아이디 , 2.비밀번호, 3. 둘다
    boolean memberLogin(MemberDto member) throws Exception;

}
