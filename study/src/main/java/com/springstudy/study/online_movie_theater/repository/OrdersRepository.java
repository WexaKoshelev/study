package com.springstudy.study.online_movie_theater.repository;

import com.springstudy.study.online_movie_theater.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository
        extends GenericRepository<Orders> {
//    Page<Orders> getOrderUserId (Long id, Pageable pageRequest);
}
