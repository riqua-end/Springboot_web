package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository <T, ID> extends Repository<T, ID>{
    // create , 기존에 데이터가 없으면 save
    // update , 기존에 데이터가 있으면 update
    T save(T data);
    // read
    Optional<T> findById(ID id);
    // Optional<T>은 값이 존재할 수도 있고 없을 수도 있는 값을 표현하는 클래스로,
    // null 대신 사용되며 안전한 값 처리와 코드의 가독성을 향상시킨다.
    // Null 포인터 예외 회피: Optional<T>을 사용하면 코드에서 발생할 수 있는 NullPointerException을 회피할 수 있습니다.
    //                     Optional 객체의 메서드를 사용하여 값에 접근할 때, 값이 존재하지 않는 경우에도 예외가 발생하지 않습니다.
    // 함수형 프로그래밍 지원: Optional<T>은 함수형 프로그래밍 스타일을 지원합니다.
    //                     map(), filter(), flatMap() 등의 메서드를 사용하여 Optional 객체의 값을 안전하게 처리할 수 있습니다.

    List<T> findAll();

    // delete
    void delete(ID id);
}
