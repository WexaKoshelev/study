package com.springstudy.study.homework.DZ7.controller.rest;

import com.springstudy.study.homework.DZ7.config.jwt.JWTTokenUtil;
import com.springstudy.study.homework.DZ7.dto.LoginDTO;
import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.service.GenericService;
import com.springstudy.study.homework.DZ7.service.UserService;
import com.springstudy.study.homework.DZ7.service.userdetails.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователь", description = "Контроллер для работы с ползоателями")
public class UsersController extends GenericController <Users, UserDTO> {
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTTokenUtil jwtTokenUtil;
    private final UserService userService;

    public UsersController(GenericService<Users, UserDTO> genericService,
                           CustomUserDetailsService customUserDetailsService,
                           JWTTokenUtil jwtTokenUtil, UserService userService) {
        super(genericService);
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDTO.getLogin());
        log.info("foundUser: {}", foundUser);
        if (!userService.checkPassword(loginDTO.getPassword(), foundUser)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации! \n Неверный пароль...");
        }
        String token = jwtTokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("username", foundUser.getUsername());
        response.put("role", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);
    }

}

