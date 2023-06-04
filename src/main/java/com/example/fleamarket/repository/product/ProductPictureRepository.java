package com.example.fleamarket.repository.product;

import com.example.fleamarket.entity.product.Product;
import com.example.fleamarket.entity.product.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Long> {
    ProductPicture findProductPicturesByProductIdx(Product productIdx);
}
