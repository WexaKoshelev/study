package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.RoleDTO;
import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.mapper.GenericMapper;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<Users, UserDTO> {
    public UserService(GenericRepository<Users> repository, GenericMapper<Users, UserDTO> mapper) {
        super(repository, mapper);
    }
    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}
