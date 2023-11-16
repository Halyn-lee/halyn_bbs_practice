package com.halyn.service;

import com.halyn.dto.BoardDto;
import com.halyn.dto.CommentDto;
import com.halyn.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDto> selectCommentList(int boardIdx) throws Exception {
        return commentMapper.selectCommentList(boardIdx);
    }

    @Override
    public boolean insertComment(CommentDto comment) throws Exception {
        return commentMapper.insertComment(comment) > 0 ? true : false;
    }
}
