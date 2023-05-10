package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.mapper.OrdersMapper;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService extends GenericService<Orders, OrdersDTO> {
    protected OrdersService(OrdersRepository repository, OrdersMapper mapper) {
        super(repository, mapper);
    }
}
