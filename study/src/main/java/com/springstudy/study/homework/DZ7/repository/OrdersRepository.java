package com.springstudy.study.homework.DZ7.repository;

import com.springstudy.study.homework.DZ7.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository
        extends GenericRepository<Orders> {
//    Page<Orders> getOrderUserId (Long id, Pageable pageRequest);
}
