package com.example.fleamarket.entity.post;

import com.example.fleamarket.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentIdx;

    @Column
    private String content;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postIdx;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userIdx;
}
