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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String openBoardList(PageDto pageDto, Model model, RedirectAttributes redirectAttributes) throws Exception {
        List<BoardDto> list = boardService.selectBoardList(pageDto);
        if (list.isEmpty() && pageDto.getNowPage() > 1) {
            pageDto.setNowPage(pageDto.getNowPage() - 1);
            redirectAttributes.addFlashAttribute("pageDto", pageDto);
            return "redirect:/board/openBoardList.do?nowPage=" + pageDto.getNowPage();
        }
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
