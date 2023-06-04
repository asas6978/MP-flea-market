package com.example.fleamarket.repository.product;

import com.example.fleamarket.entity.post.PostPicture;

import java.util.List;

public interface ProductPictureCustomRepository {
    List<PostPicture> findPostPicturesByUserIdx(Long userIdx);
}
