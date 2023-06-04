package com.example.fleamarket.entity.product;

import com.example.fleamarket.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productIdx;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private Timestamp date;

    @Column
    private String content;

    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userIdx;

    public Product(String name, int price, Timestamp date, String content, User userIdx) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.content = content;
        this.userIdx = userIdx;
    }
}
