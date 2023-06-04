package com.example.fleamarket.dto.post;

import com.example.fleamarket.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponseDto {
    private Post post;
    private String path;
    private String message;
}
