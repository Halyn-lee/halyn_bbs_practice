package com.halyn.controller;

import java.util.List;

import com.halyn.dto.CommentDto;
import com.halyn.dto.PageDto;
import com.halyn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.halyn.dto.BoardDto;
import com.halyn.service.BoardService;
@Controller
public class MainController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/board/openBoardList.do")
    public String openBoardList(PageDto pageDto, Model model) throws Exception {
        List<BoardDto> list = boardService.selectBoardList(pageDto);
        pageDto.setTotalItemCount(boardService.selectBoardItemCount());
        model.addAttribute("list", list);
        model.addAttribute("pageDto", pageDto);
        return "/board/boardList";
    }

    @GetMapping("/board/openBoardWrite.do")
    public String openBoardWrite(Model model,
                                 @RequestParam(required = false) String error) throws Exception {
        if (error != null && error.equals("-1")) {
            model.addAttribute("error", "error");
        }
        return "/board/boardWrite";
    }

    @PostMapping("/board/openBoardWrite.do")
    public String insertBoard(Model model, BoardDto board) throws Exception {
        System.out.println(board.toString());
        if (!boardService.insertBoard(board)) {
            return "redirect:/board/openBoardWrite.do?error=-1";
        }
        return "redirect:/board/openBoardList.do";
    }

    @GetMapping("/board/openBoardDetail.do")
    public String openBoardDetail(@RequestParam("boardIdx") int boardIdx, Model model, PageDto pageDto, CommentDto comment) throws Exception {

        BoardDto board = boardService.selectBoardDetail(boardIdx);
        model.addAttribute("board", board);
        model.addAttribute("pageDto", pageDto);

        List<CommentDto> commentList = commentService.selectCommentList(comment.getBoardIdx());
        model.addAttribute("commentList", commentList);

        return "/board/boardDetail";
    }

    @GetMapping("/board/openBoardEdit.do")
    public String openBoardEdit(@RequestParam("boardIdx") int boardIdx, Model model, PageDto pageDto) throws Exception {
        BoardDto board = boardService.selectBoardDetail(boardIdx);
        model.addAttribute("board", board);
        model.addAttribute("pageDto", pageDto);

        return "/board/boardEdit";
    }

    @PostMapping("/board/updateBoard.do")
    public String updateBoard(BoardDto board, PageDto pageDto) throws Exception {
        boardService.updateBoard(board);
        int boardIdx = board.getBoardIdx();
        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx + "&nowPage=" + nowPage;
    }

    @PostMapping("/board/deleteBoard.do")
    public String deleteBoard(@RequestParam("boardIdx") int boardIdx, PageDto pageDto) throws Exception {
        boardService.deleteBoard(boardIdx);
        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardList.do?nowPage=" + nowPage;
    }

    @PostMapping("/board/openCommentWrite.do")
    public String insertComment(@RequestParam("boardIdx") int boardIdx, CommentDto comment, PageDto pageDto) throws Exception {
        comment.setBoardIdx(boardIdx);
        commentService.insertComment(comment);
        commentService.selectCommentList(comment.getBoardIdx());

        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx + "&nowPage=" + nowPage;
    }

//    @PostMapping("/board/deleteComment.do")
//    public String deleteComment(@RequestParam("boardIdx") int boardIdx, PageDto pageDto) throws Exception {
//        commentService.deleteComment(commentIdx);
//        int nowPage = pageDto.getNowPage();
//        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx + "&nowPage=" + nowPage;
//    }
}
