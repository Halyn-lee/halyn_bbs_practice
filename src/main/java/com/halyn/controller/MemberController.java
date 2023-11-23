package com.halyn.controller;

import com.halyn.dto.MemberDto;
import com.halyn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/join")
    public String memberJoin() throws Exception {
        return "/member/join";
    }

    @PostMapping("/insertMember.do")
    public String insertMember(MemberDto member) throws Exception {
        memberService.insertMember(member);
        return "redirect:/join";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @PostMapping("/login")
    public String login_proc(MemberDto memberDto, HttpSession session, Model model) throws Exception {
        boolean result = memberService.memberLogin(memberDto);
        if (result) {
            session.setAttribute("loginId", memberDto.getLoginId());
            model.addAttribute("loginStatus", "success");
            return "redirect:/board/openBoardList.do";
        }
        return "redirect:/login?fail=true";
    }

    @PostMapping("/logout")
    public String logout_proc(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
