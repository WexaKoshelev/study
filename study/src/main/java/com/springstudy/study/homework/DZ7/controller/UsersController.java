package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователь", description = "Контроллер для работы с ползоателями")
public class UsersController extends GenericController <Users, UserDTO> {
    public UsersController(UserService userService) {
        super(userService);

    }
}
