package com.springstudy.study.homework.DZ7.controller.rest;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.service.OrdersService;
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
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер работы с заказами")
public class OrdersController extends GenericController<Orders, OrdersDTO>{
    public OrdersController(OrdersService ordersRepository) {
        super(ordersRepository);
    }

    @Operation(description = "Взять фильм в аренду/купить")
    @RequestMapping(value = "/takeMovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDTO> takeMovie (@RequestParam(value = "orderId") Long orderId,
                                                @RequestParam(value = "filmId") Long filmId,
                                                @RequestParam(value = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(((OrdersService)service).takeMovie(orderId, filmId, userId));
    }
}
