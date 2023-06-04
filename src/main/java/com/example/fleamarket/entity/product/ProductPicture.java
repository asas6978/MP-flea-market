package com.example.fleamarket.entity.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Product_Picture")
@NoArgsConstructor
@Data
public class ProductPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long pictureIdx;

    @Column
    private String name;

    @Column
    private String path;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productIdx;

    @Transient
    private MultipartFile picture;

    public ProductPicture(String name, String path, MultipartFile picture, Product productIdx) {
        this.name = name;
        this.path = path;
        this.picture = picture;
        this.productIdx = productIdx;
    }
}
