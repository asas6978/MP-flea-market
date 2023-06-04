package com.example.fleamarket.repository.post;

import com.example.fleamarket.entity.post.Post;
import com.example.fleamarket.entity.post.PostPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPictureRepository extends JpaRepository<PostPicture, Long> {
    PostPicture findPostPicturesByPostIdx(Post postIdx);
}
