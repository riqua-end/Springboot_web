package com.example.simpleboard.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

    private Integer page; // 현재 페이지

    private Integer size; // 총 사이즈

    private Integer currentElements; // 현재 가지고 있는 엘리먼트가 몇 개 인지

    private Integer totalPage; // 전체 페이지

    private Long totalElements; // 전체 엘리먼트
}
