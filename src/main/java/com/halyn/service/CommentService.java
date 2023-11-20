package com.halyn.service;

import com.halyn.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> selectCommentList(int boardIdx) throws Exception;
    boolean insertComment(CommentDto comment) throws Exception;
    int deleteComment(int commentIdx) throws Exception;
    void updateComment(CommentDto comment) throws Exception;


}
