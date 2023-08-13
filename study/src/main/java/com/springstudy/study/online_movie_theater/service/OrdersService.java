package com.springstudy.study.online_movie_theater.service;

import com.springstudy.study.online_movie_theater.dto.FilmDTO;
import com.springstudy.study.online_movie_theater.dto.OrdersDTO;
import com.springstudy.study.online_movie_theater.mapper.OrdersMapper;
import com.springstudy.study.online_movie_theater.model.Orders;
import com.springstudy.study.online_movie_theater.repository.FilmsRepository;
import com.springstudy.study.online_movie_theater.repository.OrdersRepository;
import com.springstudy.study.online_movie_theater.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
//    public void returnFilm (Long id) {
//        OrdersDTO ordersDTO = getOne(id);
//        ordersDTO.setPurchase(true);
//       FilmDTO filmDTO = ordersDTO.getFilmDTO();
//       update(ordersDTO);
//       filmsService.update(filmDTO);
//    }
//    public Page<OrdersDTO> listUserRentFilms(final Long id,
//                                             final Pageable pageRequest) {
//        Page<Orders> objects = ((OrdersRepository) repository).getOrderUserId(id, pageRequest);
//        List<OrdersDTO> results = mapper.toDTOs(objects.getContent());
//        return new PageImpl<>(results, pageRequest, objects.getTotalElements());
//    }

}
