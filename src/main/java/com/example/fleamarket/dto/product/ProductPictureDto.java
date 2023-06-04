package com.example.fleamarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPictureDto {
    private String name;
    private String path;
    private Long productIdx;
}
