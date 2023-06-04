package com.example.fleamarket.repository.post;

import com.example.fleamarket.entity.User;
import com.example.fleamarket.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserIdx(User user);
}
