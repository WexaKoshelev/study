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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersService extends GenericService<Orders, OrdersDTO> {
    private  final FilmsService filmsService;
    private final FilmsRepository filmsRepository;
    private final UserRepository userRepository;
    protected OrdersService(OrdersRepository repository, OrdersMapper mapper, FilmsService filmsService, FilmsRepository filmsRepository,
                            UserRepository userRepository) {
        super(repository, mapper);
        this.filmsService = filmsService;
        this.filmsRepository = filmsRepository;
        this.userRepository = userRepository;
    }
//    public OrdersDTO takeMovie (Long userId, Long filmId, Long orderId){
//        Films films = filmsRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден"));
//        Users users = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
//        OrdersDTO orders = getOne(orderId);
//        orders = getOne(userId);
//        orders.getUserId().add(users.getId());
//        orders.getFilmId().add(films.getId());
//        update(orders);
//        return orders;
//    }

    public final OrdersDTO rentFilm (OrdersDTO rentOrdersDTO) {
        FilmDTO filmDTO = filmsService.getOne(rentOrdersDTO.getId());
        filmsService.update(filmDTO);
        long rentPeriod = rentOrdersDTO.getRentPeriod() != null ? rentOrdersDTO.getRentPeriod() : 14L;
        rentOrdersDTO.setRentData(LocalDateTime.now());
        rentOrdersDTO.setRentPeriod((int) rentPeriod);
        rentOrdersDTO.setPurchase(false);
        rentOrdersDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDTO(repository.save(mapper.toEntity(rentOrdersDTO)));
    }
    public void returnFilm (Long id) {
        OrdersDTO ordersDTO = getOne(id);
        ordersDTO.setPurchase(true);
       FilmDTO filmDTO = ordersDTO.getFilmDTO();
       update(ordersDTO);
       filmsService.update(filmDTO);
    }
    public Page<OrdersDTO> listUserRentFilms(final Long id,
                                             final Pageable pageRequest) {
        Page<Orders> objects = ((OrdersRepository) repository).getOrderUserId(id, pageRequest);
        List<OrdersDTO> results = mapper.toDTOs(objects.getContent());
        return new PageImpl<>(results, pageRequest, objects.getTotalElements());
    }

}
