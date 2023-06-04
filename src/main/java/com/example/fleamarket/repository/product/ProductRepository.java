package com.example.fleamarket.repository.product;

import com.example.fleamarket.entity.User;
import com.example.fleamarket.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductIdx(Long productIdx);
    List<Product> findProductsByUserIdx(User userIdx);
}
