package com.halyn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private int commentIdx;
    private int boardIdx;
    private String creatorId;
    private String contents;
    private String createdDatetime;
    private String updatedDatetime;

}
