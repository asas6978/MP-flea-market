package com.example.fleamarket.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.fleamarket.constant.PostConst;
import com.example.fleamarket.dto.post.PostDto;
import com.example.fleamarket.dto.post.PostResponseDto;
import com.example.fleamarket.entity.User;
import com.example.fleamarket.entity.post.Post;
import com.example.fleamarket.entity.post.PostPicture;
import com.example.fleamarket.exception.PictureException;
import com.example.fleamarket.exception.PostException;
import com.example.fleamarket.exception.ProductException;
import com.example.fleamarket.repository.post.PostPictureRepository;
import com.example.fleamarket.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final PostPictureRepository pictureRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public PostResponseDto save(MultipartFile picture, PostDto postDto) {
        try {
            Post post = postRepository.save(new Post(postDto.getContent(), userService.getUserInfo(postDto.getUserIdx())));
            return new PostResponseDto(
                    post,
                    savePicture(uploadPicture(picture, post)),
                    PostConst.SUCCESS_SAVE
            );
        } catch (Exception e) {
            log.info("", e);
            throw new PostException(PostConst.FAILED_SAVE);
        }
    }

    private String savePicture(PostPicture picture) {
        try {
            PostPicture savedPicture = pictureRepository.save(picture);
            return savedPicture.getPath();
        } catch (Exception e) {
            log.info("", e);
            throw new PictureException(PostConst.FAILED_SAVE, e);
        }
    }

    private PostPicture uploadPicture(MultipartFile picture, Post post) {
        try {
            String originalName = picture.getOriginalFilename();
            String savedName = String.valueOf(UUID.randomUUID());
            String path= "https://raonaru-bucket.s3.ap-northeast-2.amazonaws.com/picture/" + savedName;
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentType(picture.getContentType());
            metadata.setContentLength(picture.getSize());
            amazonS3Client.putObject(bucket + "/picture", savedName, picture.getInputStream(), metadata);

            return new PostPicture(originalName, path, picture, post);
        } catch (IOException e) {
            log.info("", e);
            throw new PictureException(PostConst.FAILED_SAVE, e);
        }
    }

    public PostResponseDto getPost(Long postIdx) {
        try {
            Optional<Post> post = postRepository.findById(postIdx);
            if (post.isEmpty()) {
                throw new PostException(PostConst.NOT_EXIST);
            }

            return new PostResponseDto(
                    post.get(),
                    pictureRepository.findPostPicturesByPostIdx(post.get()).getPath(),
                    PostConst.EXIST);
        } catch (Exception e) {
            throw new PostException(PostConst.NOT_EXIST, e);
        }
    }

    public List<PostResponseDto> getPosts(Long userIdx) {
        try {
            User user = userService.getUserInfo(userIdx);
            List<Post> posts = postRepository.findPostsByUserIdx(user);
            List<Long> postIdxs = new ArrayList<>();

            for (Post post : posts) {
                Long temp = post.getPostIdx();
                log.info(String.valueOf(temp));
                postIdxs.add(temp);
            }

            List<PostResponseDto> result = new ArrayList<>();
            for (Long postIdx : postIdxs) {
                result.add(getPost(postIdx));
            }

            return result;
        } catch (Exception e) {
            throw new ProductException(PostConst.NOT_EXIST, e);
        }
    }
}
