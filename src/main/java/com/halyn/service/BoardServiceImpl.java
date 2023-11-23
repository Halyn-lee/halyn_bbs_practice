package com.halyn.service;

import java.util.List;

import com.halyn.dto.PageDto;
import com.halyn.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halyn.dto.BoardDto;
import com.halyn.mapper.BoardMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> selectBoardList(PageDto pageDto) throws Exception {
        List<BoardDto> list = boardMapper.selectBoardList(pageDto);
        int totalItemCount = boardMapper.selectBoardItemCount();

        if (list.isEmpty() && pageDto.getNowPage() > 1) {
            pageDto.setNowPage(pageDto.getNowPage() - 1);
            list = boardMapper.selectBoardList(pageDto);
        }
        pageDto.setTotalItemCount(totalItemCount);

        return list;
    }

    @Override
    public int selectBoardItemCount() {
        return boardMapper.selectBoardItemCount();
    }

    @Override
    public boolean insertBoard(BoardDto board) throws Exception {

        String title = board.getTitle();
        String content = board.getContents();
        String writer = board.getCreatorId();

        if (TextUtil.removeSpaceAndLine(title).length() == 0) {
            return false;
        } else if (TextUtil.removeSpaceAndLine(content).length() == 0) {
            return false;
        } else if (writer.length() == 0) {
            return false;
        } else if (title.length() >= 100) {
            return false;
        } else if (content.length() >= 2048) {
            return false;
        }

        // insert 성공한 갯수가 0보다 크면 true
        return (boardMapper.insertBoard(board) > 0) ? true : false;
    }

    @Override
    public BoardDto selectBoardDetail(int boardIdx) throws Exception {
        BoardDto board = boardMapper.selectBoardDetail(boardIdx);
        boardMapper.updateHitCount(boardIdx);

        return board;
    }

    @Override
    public int updateBoard(BoardDto board) throws Exception {
        return boardMapper.updateBoard(board);
    }


    @Override
    @Transactional
    public int deleteBoard(int boardIdx) throws Exception {
        return boardMapper.deleteBoard(boardIdx);
    }
}
