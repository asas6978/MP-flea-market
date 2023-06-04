package com.example.fleamarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long userIdx;
    private String name;
    private int price;
    private Timestamp date;
    private String content;
}
