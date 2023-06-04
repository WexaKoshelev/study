package com.springstudy.study.homework.DZ7.repository;

import com.springstudy.study.homework.DZ7.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends GenericRepository<Users> {
}

