package com.halyn.service;

import com.halyn.dto.MemberDto;
import com.halyn.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public void insertMember(MemberDto member) throws Exception {
        memberMapper.insertMember(member);
    }

    @Override
    public MemberDto selectMember(String loginId) throws Exception {
        return memberMapper.selectMember(loginId);
    }

    @Override
    public boolean memberLogin(MemberDto member) throws Exception {
        String inputLoginId = member.getLoginId();
        String inputPassword = member.getPassword();
        MemberDto dbMemberDto = selectMember(inputLoginId);

        if (dbMemberDto != null) {
            String dbPassword = dbMemberDto.getPassword();
            if (inputPassword.equals(dbPassword)) {
                return true;
            }
        }
        return false;
    }

//    public boolean memberLogin2(MemberDto member) throws Exception {
//        MemberDto dbMemberDto = selectMember(member.getLoginId());
//
//        if (dbMemberDto != null) {
//            if (member.getPassword().equals(dbMemberDto.getPassword())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
