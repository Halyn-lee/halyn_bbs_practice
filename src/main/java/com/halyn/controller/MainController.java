package com.halyn.controller;

import java.util.List;

import com.halyn.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.halyn.dto.BoardDto;
import com.halyn.service.BoardService;

@Controller
public class MainController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/board/openBoardList.do")
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

    @RequestMapping("/board/openBoardDetail.do")
    public String openBoardDetail(int boardIdx, Model model, PageDto pageDto) throws Exception {

        BoardDto board = boardService.selectBoardDetail(boardIdx);
        model.addAttribute("board", board);
        model.addAttribute("pageDto", pageDto);

        return "/board/boardDetail";
    }

    @RequestMapping("/board/openBoardEdit.do")
    public String openBoardEdit(int boardIdx, Model model, PageDto pageDto) throws Exception {
        BoardDto board = boardService.selectBoardDetail(boardIdx);
        model.addAttribute("board", board);
        model.addAttribute("pageDto", pageDto);

        return "/board/boardEdit";
    }

    @RequestMapping("/board/updateBoard.do")
    public String updateBoard(BoardDto board, PageDto pageDto) throws Exception {
        boardService.updateBoard(board);
        int boardIdx = board.getBoardIdx();
        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx +"&nowPage=" + nowPage;
    }

    @RequestMapping("/board/deleteBoard.do")
    public String deleteBoard(int boardIdx, PageDto pageDto) throws Exception {
        boardService.deleteBoard(boardIdx);
        int nowPage = pageDto.getNowPage();
        return "redirect:/board/openBoardList.do?nowPage=" + nowPage;
    }
}