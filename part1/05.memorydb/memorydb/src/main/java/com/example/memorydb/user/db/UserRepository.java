package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    // 사용자 10명을 생성 후 70점 이상의 사용자의 정보를 찾아주는 method 작성하기
    public List<UserEntity> findAllScoreGreaterThen(int score){
        return this.findAll().stream()
                .filter(
                        it->{
                            return it.getScore() >= score;
                        }
                ).collect(Collectors.toList());
    }
}
