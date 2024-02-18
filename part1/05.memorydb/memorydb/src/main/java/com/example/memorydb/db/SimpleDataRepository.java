package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

// Entity 타입의 데이터를 저장하고 관리하는 추상 클래스입니다.
abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    // 데이터를 저장할 리스트입니다.
    private List<T> dataList = new ArrayList<T>();

    // 데이터의 인덱스를 관리하는 변수입니다.
    private static long index = 0;

    // 데이터를 정렬하기 위한 Comparator 인터페이스의 익명 구현체입니다.
    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            // 데이터의 ID를 기준으로 정렬합니다.
            return Long.compare(o1.getID(),o2.getID());
        }
    };

    // 데이터를 저장하거나 업데이트합니다.
    @Override
    public T save(T data) {
        // 입력된 데이터가 null인지 확인합니다.
        if (Objects.isNull(data)){
            // null이면 예외를 발생시킵니다.
            throw new RuntimeException("Data is null");
        }

        // DB에 데이터가 있는지 확인합니다.

        // Stream에서 요소를 필터링한 후, 첫 번째로 발견된 요소를 Optional 객체로 반환합니다.
        // filter() 메서드는 주어진 조건을 만족하는 요소들로 이루어진 Stream을 반환합니다.
        // findFirst() 메서드는 Stream에서 가장 첫 번째로 발견된 요소를 Optional 객체로 감싸서 반환합니다.
        // 만약 Stream이 비어있다면 빈 Optional을 반환합니다.
        var prevData = dataList.stream()
                .filter(it ->{
                    return it.getID().equals(data.getID()); // ID가 같은 요소를 필터링합니다.
                })
                .findFirst();

        // 이전 데이터가 있는 경우
        if (prevData.isPresent()) { // .isPresent() 는 Optional객체의 값이 null이면 false,아니면 true를 반환
            // 이전 데이터를 삭제하고 새로운 데이터로 대체합니다.
            dataList.remove(prevData);
            dataList.add(data);
        }
        else {
            // 이전 데이터가 없는 경우 새로운 데이터를 추가하고 인덱스를 증가시킵니다.
            index++;
            data.setId(index);
            dataList.add(data);
        }

        return data;
    }

    // ID에 해당하는 데이터를 찾습니다.
    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it ->{
                    return (it.getID().equals(id));
                })
                .findFirst();
    }

    // 모든 데이터를 정렬하여 반환합니다.
    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    // ID에 해당하는 데이터를 삭제합니다.
    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it ->{
                    return (it.getID().equals(id));
                })
                .findFirst();

        // 삭제할 데이터가 존재하는 경우 삭제합니다.
        if (deleteEntity.isPresent()){
            dataList.remove(deleteEntity);
        }
    }
}
