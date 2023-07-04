package com.springstudy.study.homework.DZ7.mapper;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import com.springstudy.study.homework.DZ7.service.FilmsService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class OrdersMapper extends GenericMapper<Orders, OrdersDTO> {
    private final FilmsRepository filmsRepository;
    private final UserRepository userRepository;
    private final FilmsService filmsService;

    public OrdersMapper(ModelMapper modelMapper, FilmsRepository filmsRepository, UserRepository userRepository, FilmsService filmsService) {
        super(Orders.class, OrdersDTO.class, modelMapper);
        this.filmsRepository = filmsRepository;
        this.userRepository = userRepository;
        this.filmsService = filmsService;
    }
    @PostConstruct
    protected void setupMapper() {
        super.modelMapper.createTypeMap(Orders.class, OrdersDTO.class)
                .addMappings(m -> m.skip(OrdersDTO :: setUserId))
                .addMappings(m -> m.skip(OrdersDTO :: setFilmId))
                .setPostConverter(toDTOConverter());
        super.modelMapper.createTypeMap(OrdersDTO.class, Orders.class)
                .addMappings(m -> m.skip(Orders :: setUsers))
                .addMappings(m -> m.skip(Orders :: setFilms))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrdersDTO source, Orders destination) {
        destination.setFilms(filmsRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
        destination.setUsers(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }
    @Override
    protected void mapSpecificFields(Orders source, OrdersDTO destination) {
        destination.setUserId(source.getUsers().getId());
        destination.setFilmId(source.getFilms().getId());
        destination.setFilmDTO(filmsService.getOne(source.getFilms().getId()));
    }
    @Override
    protected List<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод не доступен");
    }
}
