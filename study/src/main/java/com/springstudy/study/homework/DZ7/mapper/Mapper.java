package com.springstudy.study.homework.DZ7.mapper;

import com.springstudy.study.homework.DZ7.dto.GenericDTO;
import com.springstudy.study.homework.DZ7.model.GenericModel;

import java.util.List;

public interface Mapper <E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);
    D toDTO(E entity);
    List<E> toEntities(List<D> dtos);
    List<D> toDTOs(List<E> entities);
}
