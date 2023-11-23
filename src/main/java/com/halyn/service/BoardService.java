package com.halyn.service;

import java.util.List;
import com.halyn.dto.BoardDto;
import com.halyn.dto.PageDto;

public interface BoardService {

    List<BoardDto> selectBoardList(PageDto pageDto) throws Exception;

    int selectBoardItemCount();
    boolean insertBoard(BoardDto board) throws Exception;
    BoardDto selectBoardDetail(int boardIdx) throws Exception;
    int updateBoard(BoardDto board) throws Exception;
    int deleteBoard(int boardIdx) throws Exception;
}