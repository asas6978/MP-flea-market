package com.example.fleamarket.entity.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Post_Picture")
@Data
@NoArgsConstructor
public class PostPicture {
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
    @JoinColumn(name = "post_id")
    private Post postIdx;

    @Transient
    private MultipartFile picture;

    public PostPicture(String name, String path, MultipartFile picture, Post post) {
        this.name = name;
        this.path = path;
        this.picture = picture;
        this.postIdx = post;
    }
}
