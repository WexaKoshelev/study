package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import com.springstudy.study.homework.DZ7.service.GenericService;
import com.springstudy.study.homework.DZ7.service.OrdersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/info")
@Tag(name = "Заказы", description = "Контроллер работы с заказами")
public class OrdersController extends GenericController<Orders, OrdersDTO>{
    public OrdersController(OrdersService ordersRepository) {
        super(ordersRepository);
    }
}
