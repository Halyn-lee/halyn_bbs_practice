package com.halyn.mapper;

import com.halyn.dto.BoardDto;
import com.halyn.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto> selectCommentList(int boardIdx) throws Exception;

    int insertComment(CommentDto comment) throws Exception;

}
