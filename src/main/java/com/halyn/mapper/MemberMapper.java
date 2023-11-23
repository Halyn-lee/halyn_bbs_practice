package com.halyn.mapper;

import com.halyn.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDto member) throws Exception;
    MemberDto selectMember(String loginId) throws Exception;
}
