package com.springstudy.study.homework.DZ7.controller;

import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер работы с заказами")
public class OrdersController extends GenericController<Orders>{
    private final OrdersRepository ordersRepository;

    public OrdersController(GenericRepository<Orders> genericRepository, OrdersRepository ordersRepository) {
        super(genericRepository);
        this.ordersRepository = ordersRepository;
    }
}
