package com.halyn.service;

import com.halyn.dto.BoardDto;
import com.halyn.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> selectCommentList(int boardIdx) throws Exception;

    boolean insertComment(CommentDto comment) throws Exception;

}
