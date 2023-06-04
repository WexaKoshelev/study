package com.springstudy.study.homework.DZ7.controller.rest;

import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователь", description = "Контроллер для работы с ползоателями")
public class UsersController extends GenericController <Users, UserDTO> {

    public UsersController(UserService userService) {
        super(userService);
    }
    @Operation(description = "Добавить заказ к пользователю")
    @RequestMapping(value = "/addOrders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addOrder(@RequestParam(value = "userId") Long userId,
                                            @RequestParam(value = "orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(((UserService)service).addOrder(userId, orderId));
    }
//    @Operation(description = "Список заказов ")
//    @RequestMapping(value = "/allOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public  ResponseEntity<UserDTO> allOrders(){
//        return  ResponseEntity.status(HttpStatus.OK).body(((UserService)service).allOrders();
//    }
}

