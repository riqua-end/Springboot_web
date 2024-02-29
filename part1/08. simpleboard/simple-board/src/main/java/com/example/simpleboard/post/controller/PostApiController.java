package com.example.simpleboard.post.controller;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("")
    public PostEntity create(
        @Valid
        @RequestBody PostRequest postRequest
    ){
        return postService.create(postRequest);
    }

    @PostMapping("/view") // 익명게시판 , 본인이 작성한 글을 보려면 password가 필요함
    public PostEntity view(
        @Valid
        @RequestBody PostViewRequest postViewRequest
    ){
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> list(
        @PageableDefault(page = 0, size = 10 ,sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable
    ){
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    )
    {
        postService.delete(postViewRequest);
    }
}
