package com.example.fleamarket.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CommentDto {
    private String content;
    private Timestamp createAt;
    private Long postIdx;
    private Long userIdx;
}
