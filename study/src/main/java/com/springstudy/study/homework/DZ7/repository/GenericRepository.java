package com.springstudy.study.homework.DZ7.repository;

import com.springstudy.study.homework.DZ7.model.GenericModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends GenericModel>
        extends JpaRepository<T, Long> {
}

