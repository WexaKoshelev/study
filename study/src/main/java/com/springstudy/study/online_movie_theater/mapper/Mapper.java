package com.springstudy.study.online_movie_theater.mapper;

import com.springstudy.study.online_movie_theater.dto.GenericDTO;
import com.springstudy.study.online_movie_theater.model.GenericModel;

import java.util.List;

public interface Mapper <E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);
    D toDTO(E entity);
    List<E> toEntities(List<D> dtos);
    List<D> toDTOs(List<E> entities);
}
