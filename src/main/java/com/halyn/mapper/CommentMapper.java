package com.halyn.mapper;

import com.halyn.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> selectCommentList(int boardIdx) throws Exception;
    int insertComment(CommentDto comment) throws Exception;
    int deleteComment(int commentIdx) throws Exception;
    void updateComment(CommentDto comment) throws Exception;
}
