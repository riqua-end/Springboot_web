package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostEntity create(
        PostRequest postRequest
    ){
        var entity = PostEntity.builder()
                .boardId(1L) // << 임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    /*
    * 1. 게시글이 있는가?
    * 2. 비밀번호가 맞는가?
    * */
    public PostEntity view(PostViewRequest postViewRequest) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map( it->{
                    // entity 존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(),postViewRequest.getPassword()));
                    }

                    return it;

                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : " +postViewRequest.getPostId());
                        }
                );
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map( it->{
                    // entity 존재
                    if (!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(),postViewRequest.getPassword()));
                    }

                    it.setStatus("UNREGISTERED"); // 삭제가 아니라 단순 상태만 변경
                    postRepository.save(it);
                    return it;

                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : " +postViewRequest.getPostId());
                        }
                );
    }
}
