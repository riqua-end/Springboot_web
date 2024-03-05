package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * dto -> entity -> dto
 */

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private Converter<DTO,ENTITY> converter;
    @Autowired(required = false)
    private JpaRepository<ENTITY,Long> jpaRepository;

    @Override
    public DTO create(DTO dto) {

        // dto -> entity
        var entity = converter.toEntity(dto);
        // entity -> save
        jpaRepository.save(entity);
        // save -> dto

        return converter.toDto(entity);
    }

    @Override
    public Optional<DTO> read(Long id) {

        var optionalEntity = jpaRepository.findById(id);

        var dto = optionalEntity.map(
                it->{
                    return converter.toDto(it);
                }
        ).orElseGet(()->null);

        return Optional.ofNullable(dto);
    }

    @Override
    public DTO update(DTO dto) {

        var entity = converter.toEntity(dto);
        jpaRepository.save(entity);

        return converter.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {

        var list = jpaRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalPage(list.getTotalPages())
                .totalElements(list.getTotalElements())
                .build();

        var dtoList = list.stream()
                .map(it->{
                    return converter.toDto(it);
                }).collect(Collectors.toList());

        return Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
    }
}
