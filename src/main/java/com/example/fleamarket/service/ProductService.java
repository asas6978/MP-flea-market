package com.example.fleamarket.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.fleamarket.constant.ProductConst;
import com.example.fleamarket.dto.product.ProductDto;
import com.example.fleamarket.dto.product.ProductResponseDto;
import com.example.fleamarket.entity.User;
import com.example.fleamarket.entity.product.Product;
import com.example.fleamarket.entity.product.ProductPicture;
import com.example.fleamarket.exception.PictureException;
import com.example.fleamarket.exception.ProductException;
import com.example.fleamarket.repository.product.ProductPictureRepository;
import com.example.fleamarket.repository.product.ProductRepository;
import com.example.fleamarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductPictureRepository pictureRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ProductResponseDto save(MultipartFile picture, ProductDto productDto) {
        try {
            Optional<User> user = userRepository.findById(productDto.getUserIdx());
            if (user.isEmpty()) {
                throw new ProductException();
            }

            Product product = new Product(
                    productDto.getName(),
                    productDto.getPrice(),
                    productDto.getDate(),
                    productDto.getContent(),
                    user.get());
            productRepository.save(product);

            return new ProductResponseDto(
                    product,
                    savePicture(uploadPicture(picture, product)),
                    ProductConst.SUCCESS_SAVE);
        } catch (Exception e) {
            log.info("", e);
            throw new ProductException(ProductConst.FAILED_SAVE, e);
        }
    }

    private String savePicture(ProductPicture picture) {
        try {
            ProductPicture savedPicture = pictureRepository.save(picture);
            return savedPicture.getPath();
        } catch (Exception e) {
            log.info("", e);
            throw new PictureException(ProductConst.FAILED_SAVE, e);
        }
    }

    private ProductPicture uploadPicture(MultipartFile picture, Product product) {
        try {
            String originalName = picture.getOriginalFilename();
            String savedName = String.valueOf(UUID.randomUUID());
            String path= "https://raonaru-bucket.s3.ap-northeast-2.amazonaws.com/picture/" + savedName;
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentType(picture.getContentType());
            metadata.setContentLength(picture.getSize());
            amazonS3Client.putObject(bucket + "/picture", savedName, picture.getInputStream(), metadata);

            return new ProductPicture(originalName, path, picture, product);
        } catch (IOException e) {
            log.info("", e);
            throw new PictureException(ProductConst.FAILED_SAVE, e);
        }
    }

    public ProductResponseDto getProduct(Long productIdx) {
        try {
            Product product = productRepository.findProductByProductIdx(productIdx);
            return new ProductResponseDto(
                    product,
                    pictureRepository.findProductPicturesByProductIdx(product).getPath(),
                    ProductConst.EXIST);
        } catch (Exception e) {
            throw new ProductException(ProductConst.NOT_EXIST, e);
        }
    }

    public List<ProductResponseDto> getProducts(Long userIdx) {
        try {
            User user = userService.getUserInfo(userIdx);
            List<Product> products = productRepository.findProductsByUserIdx(user);
            List<Long> productIdxs = new ArrayList<>();

            for (Product product : products) {
                Long temp = product.getProductIdx();
                log.info(String.valueOf(temp));
                productIdxs.add(temp);
            }

            List<ProductResponseDto> result = new ArrayList<>();
            for (Long productIdx : productIdxs) {
                result.add(getProduct(productIdx));
            }

            return result;
        } catch (Exception e) {
            throw new ProductException(ProductConst.NOT_EXIST, e);
        }
    }
}
