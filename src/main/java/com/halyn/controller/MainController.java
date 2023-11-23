package com.halyn.controller;

import com.halyn.dto.BoardDto;
import com.halyn.dto.CommentDto;
import com.halyn.dto.PageDto;
import com.halyn.service.BoardService;
import com.halyn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/board/openBoardList.do";
    }

    @GetMapping("/board/openBoardList.do")
    public String openBoardList(PageDto pageDto, Model model) throws Exception {
        List<BoardDto> list = boardService.selectBoardList(pageDto);
        model.addAttribute("list", list);
        model.addAttribute("pageDto", pageDto);
        return "/board/boardList";
    }

    @GetMapping("/board/openBoardWrite.do")
    public String openBoardWrite() throws Exception {
        return "/board/boardWrite";
    }

    @PostMapping("/board/openBoardWrite.do")
    @ResponseBody
    public Map<String, Object> insertBoard(@RequestBody BoardDto board) throws Exception {
        boolean result = boardService.insertBoard(board);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
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
    public String openBoardEdit(int boardIdx, Model model, PageDto pageDto) throws Exception {
        BoardDto board = boardService.selectBoardDetail(boardIdx);
        model.addAttribute("board", board);
        model.addAttribute("pageDto", pageDto);

        return "/board/boardEdit";
    }

    @PostMapping("/board/updateBoard.do")
    @ResponseBody
    public Map<String, Object> updateBoard(@RequestBody BoardDto board, PageDto pageDto) throws Exception {
        boolean result = boardService.updateBoard(board) > 0;
        int boardIdx = board.getBoardIdx();
        int nowPage = pageDto.getNowPage();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("boardIdx", boardIdx);
        resultMap.put("nowPage", nowPage);
        resultMap.put("result", result);
        return resultMap;
    }

    @PostMapping("/board/deleteBoard.do")
    @ResponseBody
    public Map<String, Object> deleteBoard(int boardIdx, int nowPage) throws Exception {
        boolean result = boardService.deleteBoard(boardIdx) > 0;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        resultMap.put("nowPage", nowPage);
        return resultMap;
    }

    @PostMapping("/board/openCommentWrite.do")
    public String insertComment(@RequestParam("boardIdx") int boardIdx, CommentDto comment, PageDto pageDto) throws Exception {
        comment.setBoardIdx(boardIdx);
        commentService.insertComment(comment);
        commentService.selectCommentList(comment.getBoardIdx());

        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx + "&nowPage=" + nowPage;
    }

    @PostMapping("/board/deleteComment.do")
    @ResponseBody
    public Map<String, Object> deleteComment(int commentIdx, int boardIdx, int nowPage) throws Exception {
        boolean result = commentService.deleteComment(commentIdx) > 0;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", result);
        resultMap.put("nowPage", nowPage);
        resultMap.put("boardIdx", boardIdx);
        return resultMap;
    }

    @PostMapping("/board/updateComment.do")
    public String updateComment(int commentIdx, String contents, int boardIdx, int nowPage) throws Exception {
        CommentDto updatedComment = new CommentDto();
        updatedComment.setCommentIdx(commentIdx);
        updatedComment.setContents(contents);

        commentService.updateComment(updatedComment);

        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx + "&nowPage=" + nowPage;
    }
}
