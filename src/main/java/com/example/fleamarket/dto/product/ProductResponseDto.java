package com.example.fleamarket.dto.product;

import com.example.fleamarket.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Product product;
    private String path;
    private String message;
}
