package com.example.fleamarket.controller;

import com.example.fleamarket.constant.ProductConst;
import com.example.fleamarket.dto.product.ProductDto;
import com.example.fleamarket.dto.product.ProductResponseDto;
import com.example.fleamarket.exception.PostException;
import com.example.fleamarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ProductResponseDto save(
            @RequestPart MultipartFile picture,
            @RequestPart ProductDto productDto) {
        try {
            return productService.save(picture, productDto);
        } catch (PostException e) {
            log.info("", e);
            return savingExceptionReturnValue();
        }
    }

    @GetMapping("/{productIdx}")
    public ProductResponseDto getProduct(@PathVariable(value = "productIdx") Long productIdx) {
        try {
            return productService.getProduct(productIdx);
        } catch (PostException e) {
            log.info("", e);
            return gettingExceptionReturnValue();
        }
    }

    @GetMapping("/view/{userIdx}")
    public List<ProductResponseDto> getProducts(@PathVariable(value = "userIdx") Long userIdx) {
        try {
            return productService.getProducts(userIdx);
        } catch (PostException e) {
            log.info("", e);
            return null;
        }
    }

    private ProductResponseDto savingExceptionReturnValue() {
        return new ProductResponseDto(null, null, ProductConst.FAILED_SAVE);
    }

    private ProductResponseDto gettingExceptionReturnValue() {
        return new ProductResponseDto(null, null, ProductConst.NOT_EXIST);
    }
}
