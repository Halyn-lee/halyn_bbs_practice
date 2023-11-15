package com.halyn;

import com.halyn.dto.BoardDto;
import com.halyn.service.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class HalynApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void insertBoardDummyData() throws Exception {
        BoardServiceImpl boardService = context.getBean(BoardServiceImpl.class);

        for (int i = 0; i <= 500; i++) {
            BoardDto boardDto = new BoardDto();
            boardDto.setTitle("더미 게시글 제목" + i);
            boardDto.setContents("더미 게시글 내용" + i);
            boardDto.setCreatorId("admin");
            boardDto.setCreatedDatetime(String.valueOf(LocalDateTime.now()));
            boardService.insertBoard(boardDto);
        }
    }

    @Test
    void asdf() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

}
