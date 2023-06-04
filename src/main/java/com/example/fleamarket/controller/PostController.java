package com.example.fleamarket.controller;

import com.example.fleamarket.constant.PostConst;
import com.example.fleamarket.dto.post.PostDto;
import com.example.fleamarket.dto.post.PostResponseDto;
import com.example.fleamarket.exception.PostException;
import com.example.fleamarket.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public PostResponseDto save(
            @RequestPart MultipartFile picture,
            @RequestPart PostDto postDto) {
        try {
            return postService.save(picture, postDto);
        } catch (PostException e) {
            log.info("", e);
            return savingExceptionReturnValue();
        }
    }

    @GetMapping("/{postIdx}")
    public PostResponseDto getPost(@PathVariable(value = "postIdx") Long postIdx) {
        try {
            return postService.getPost(postIdx);
        } catch (PostException e) {
            log.info("", e);
            return gettingExceptionReturnValue();
        }
    }

    @GetMapping("/view/{userIdx}")
    public List<PostResponseDto> getPosts(@PathVariable(value = "userIdx") Long userIdx) {
        try {
            return postService.getPosts(userIdx);
        } catch (PostException e) {
            log.info("", e);
            return null;
        }
    }

    private PostResponseDto savingExceptionReturnValue() {
        return new PostResponseDto(null, null, PostConst.FAILED_SAVE);
    }

    private PostResponseDto gettingExceptionReturnValue() {
        return new PostResponseDto(null, null, PostConst.NOT_EXIST);
    }
}
