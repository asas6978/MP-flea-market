package com.example.fleamarket.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPictureDto {
    private String name;
    private String path;
    private Long postIdx;
}
