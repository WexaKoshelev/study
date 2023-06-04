package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.FilmDTO;
import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.mapper.OrdersMapper;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class OrdersService extends GenericService<Orders, OrdersDTO> {
    private final FilmsRepository filmsRepository;
    private final UserRepository userRepository;
    protected OrdersService(OrdersRepository repository, OrdersMapper mapper, FilmsRepository filmsRepository,
                            UserRepository userRepository) {
        super(repository, mapper);
        this.filmsRepository = filmsRepository;
        this.userRepository = userRepository;
    }

    public OrdersDTO takeMovie (Long userId, Long filmId, Long orderId){
        Films films = filmsRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден"));
        Users users = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        OrdersDTO orders = getOne(orderId);
        orders = getOne(userId);
        orders.getUserId().add(users.getId());
        orders.getFilmId().add(films.getId());
        update(orders);
        return orders;
    }
}
