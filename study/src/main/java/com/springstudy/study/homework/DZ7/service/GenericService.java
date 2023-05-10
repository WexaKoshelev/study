package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.GenericDTO;
import com.springstudy.study.homework.DZ7.mapper.GenericMapper;
import com.springstudy.study.homework.DZ7.model.GenericModel;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class GenericService <E extends GenericModel, D extends GenericDTO> {
    protected final GenericRepository<E> repository;
    protected final GenericMapper<E,D> mapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericService(GenericRepository<E> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public List<D> listAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public D getOne(final Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Данных не найдено!")));
    }

    public D create(D newObject) {
        newObject.setDeletedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public D update(D updatedObject) {
        return mapper.toDTO(repository.save(mapper.toEntity(updatedObject)));
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }

}
