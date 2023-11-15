package com.halyn.mapper;

import java.util.List;

import com.halyn.dto.PageDto;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import com.halyn.dto.BoardDto;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectBoardList(PageDto pageDto) throws Exception;

    int selectBoardItemCount();
    int insertBoard(BoardDto board) throws Exception;
    BoardDto selectBoardDetail(int boardIdx) throws Exception;
    void updateHitCount(int boardIdx) throws Exception;
    void updateBoard(BoardDto board) throws Exception;
    void deleteBoard(int boardIdx) throws Exception;
}