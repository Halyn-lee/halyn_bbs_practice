package com.halyn.mapper;

import java.util.List;

import com.halyn.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import com.halyn.dto.BoardDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectBoardList(PageDto pageDto) throws Exception;
    List<BoardDto> selectBoardListWithPaging(@Param("startIndex") int startIndex, @Param("itemCountByPage") int itemCountByPage);
    int selectBoardItemCount();
    int insertBoard(BoardDto board) throws Exception;
    BoardDto selectBoardDetail(int boardIdx) throws Exception;
    void updateHitCount(int boardIdx) throws Exception;
    void updateBoard(BoardDto board) throws Exception;
    int deleteBoard(int boardIdx) throws Exception;

}