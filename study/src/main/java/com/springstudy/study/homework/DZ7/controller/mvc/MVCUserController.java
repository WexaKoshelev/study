package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.springstudy.study.homework.DZ7.constants.UserRolesConstants.ADMIN;

@Controller
@Slf4j
@RequestMapping("/users")
public class MVCUserController {

    private final UserService userService;

    public MVCUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userService.create(userDTO);
        return "redirect:login";
    }
}
