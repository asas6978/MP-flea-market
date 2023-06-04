package com.example.fleamarket.entity.post;

import com.example.fleamarket.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postIdx;

    @Column
    private String content;

    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userIdx;

    public Post(String content, User userIdx) {
        this.content = content;
        this.userIdx = userIdx;
    }
}
